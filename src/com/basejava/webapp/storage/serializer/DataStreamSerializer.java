package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());

            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            dos.writeInt(resume.getSections().entrySet().size()); // Запишем количество секций
            for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {  // пишем секции
                dos.writeUTF(entry.getKey().toString()); // имя секции
                String className = entry.getValue().getClass().getSimpleName();
                switch (className) {
                    case "TextSection":
                        writeTextSection((TextSection) entry.getValue(), dos);
                        break;
                    case "ListSection":
                        writeStringListSection((ListSection) entry.getValue(), dos);
                        break;
                    case "CompanySection":
                        writeCompanySection((CompanySection) entry.getValue(), dos);
                        break;
                    default:
                        throw new StorageException("Unknown SectionType name " + className);
                }
            }
        }
    }

    private void writeTextSection(TextSection section, DataOutputStream dos) throws IOException {
        dos.writeUTF(section.getClass().getName());
        dos.writeUTF(section.getText());
    }

    private void writeStringListSection(ListSection<String> section, DataOutputStream dos) throws IOException {
        dos.writeUTF(section.getClass().getName());
        dos.writeUTF("String");
        dos.writeInt(section.getItems().size());
        writeList(dos, section.getItems(), new StringReaderWriter());
    }

    private void writeCompanySection(CompanySection section, DataOutputStream dos) throws IOException {
        dos.writeUTF(section.getClass().getName());
        dos.writeUTF("CompanySection");
        dos.writeInt(section.getItems().size());
        for (int i = 0; i < section.getItems().size(); i++) {
            dos.writeUTF(section.getItems().get(i).getLink().getTitle());
            dos.writeUTF(section.getItems().get(i).getLink().getLink() == null ?
                    "" : section.getItems().get(i).getLink().getLink());
            dos.writeInt(section.getItems().get(i).getCompanyPersonalInfoList().size());
            writeList(dos, section.getItems().get(i).getCompanyPersonalInfoList(), new CompanyPersonalInfoReaderWriter());
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int countSection = dis.readInt();  //  количество секций
            for (int i = 0; i < countSection; i++) {  // читаем секции
                String sectionName = dis.readUTF(); // имя секции
                String className = dis.readUTF();  // имя класса
                className = className.substring(className.lastIndexOf(".") + 1);
                switch (className) {
                    case "TextSection":
                        resume.getSections().put(SectionType.valueOf(sectionName), readTextSection(dis));
                        break;
                    case "ListSection":
                        String itemsClassName = dis.readUTF();
                        if (itemsClassName.equals("String")) {
                            resume.getSections().put(SectionType.valueOf(sectionName), readStringListSection(dis));
                        }
                        break;
                    case "CompanySection":
                        dis.readUTF();
                        resume.getSections().put(SectionType.valueOf(sectionName),
                                readCompanySection(dis));
                        break;
                    default:
                        throw new StorageException("Unknown SectionType name " + className);
                }
            }
            return resume;
        }
    }

    private TextSection readTextSection(DataInputStream dis) throws IOException {
        TextSection textSection = new TextSection();
        textSection.setText(dis.readUTF());
        return textSection;
    }

    private ListSection<String> readStringListSection(DataInputStream dis) throws IOException {
        ListSection<String> listSection = new ListSection<>();
        int count = dis.readInt();
        List<String> stringList = readList(dis, new StringReaderWriter(), count);
        listSection.setItems(stringList);
        return listSection;
    }

    private CompanySection readCompanySection(DataInputStream dis) throws IOException {
        CompanySection companySection = new CompanySection();
        int size = dis.readInt();
        List<Company> companyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Company company = new Company();
            String title = dis.readUTF();
            String link = dis.readUTF();
            company.setLink(new HyperLink(title, link));
            int count = dis.readInt();
            List<CompanyPersonalInfo> infoList = readList(dis, new CompanyPersonalInfoReaderWriter(), count);
            company.setCompanyPersonalInfoList(infoList);
            companyList.add(company);
        }
        companySection.setItems(companyList);
        return companySection;
    }

    interface ItemReaderWriter<T> {
        void write(DataOutputStream out, T item) throws IOException;

        T read(DataInputStream in) throws IOException;
    }

    private static class StringReaderWriter implements ItemReaderWriter<String> {
        @Override
        public void write(DataOutputStream out, String item) throws IOException {
            out.writeUTF(item);
        }

        @Override
        public String read(DataInputStream in) throws IOException {
            return in.readUTF();
        }
    }

    private static class CompanyPersonalInfoReaderWriter implements ItemReaderWriter<CompanyPersonalInfo> {
        @Override
        public void write(DataOutputStream out, CompanyPersonalInfo item) throws IOException {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            out.writeUTF(item.getStart().format(format));
            out.writeUTF(item.getEnd().format(format));
            out.writeUTF(item.getText());
            out.writeUTF(item.getDescription() == null ? "" : item.getDescription());
        }

        @Override
        public CompanyPersonalInfo read(DataInputStream in) throws IOException {
            CompanyPersonalInfo info = new CompanyPersonalInfo();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            info.setStart(LocalDate.parse(in.readUTF(), format));
            info.setEnd(LocalDate.parse(in.readUTF(), format));
            info.setText(in.readUTF());
            info.setDescription(in.readUTF());
            return info;
        }
    }

    private <T> void writeList(DataOutputStream out, List<T> collection, ItemReaderWriter<T> writer) throws IOException {
        Objects.requireNonNull(writer);
        for (T t : collection) {
            writer.write(out, t);
        }
    }

    private <T> List<T> readList(DataInputStream in, ItemReaderWriter reader, int size) throws IOException {
        List<T> list = new ArrayList<T>();
        for(int i = 0; i < size; i++) {
            list.add((T) reader.read(in));
        }
        return list;
    }

}
