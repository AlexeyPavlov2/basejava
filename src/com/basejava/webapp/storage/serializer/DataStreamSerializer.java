package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    private static final int version = 1;

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeInt(version);
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            dos.flush();
            // TODO implements sections
            /*if (resume.getSections().entrySet().size() == 0) {
                throw new StorageException("Current resume does not have any sections");
            }*/
            dos.writeInt(resume.getSections().entrySet().size()); // Запишем количество секций
            dos.flush();
            for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
                dos.writeUTF(entry.getKey().toString()); // имя секции
                switch (entry.getValue().getClass().getSimpleName()) {
                    case "TextSection":
                        writeTextSection((TextSection) entry.getValue(), dos);
                        break;
                    case "ListSection":
                        writeStringListSection((ListSection) entry.getValue(), dos);
                        break;
                    case "CompanySection":
                        writeCompanySection((CompanySection) entry.getValue(), dos);
                        break;
                }
            }
        }
    }

    private void writeTextSection(TextSection section, DataOutputStream dos) throws IOException {
        dos.writeUTF(section.getClass().getName()); // имя класса
        dos.writeUTF(section.getText()); // текст секции
        dos.flush();
    }

    private void writeStringListSection(ListSection section, DataOutputStream dos) throws IOException {
        dos.writeUTF(section.getClass().getName());
        dos.writeUTF("String");
        dos.writeInt(section.getItems().size());
        section.getItems().stream().forEach(el -> {
            try {
                dos.writeUTF(el.toString());
            } catch (IOException e) {
                throw new StorageException("Can't write ListSection", e);
            }
        });
        dos.flush();
    }

    private void writeCompanySection(CompanySection section, DataOutputStream dos) throws IOException {
        dos.writeUTF(section.getClass().getName());
        dos.writeUTF("CompanySection");
        dos.writeInt(section.getItems().size());
        section.getItems().forEach(el -> {
            try {
                dos.writeUTF(el.getLink().getTitle());
                dos.writeUTF(el.getLink().getLink());
                dos.writeInt(el.getCompanyPersonalInfoList().size());
                for (CompanyPersonalInfo info : el.getCompanyPersonalInfoList()) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yyyy");
                    dos.writeUTF(info.getStart().format(format));
                    dos.writeUTF(info.getEnd().format(format));
                    dos.writeUTF(info.getText());
                    dos.writeUTF(info.getDescription());
                }
                dos.flush();
            } catch (IOException e) {
                throw new StorageException("Can't write CompanySection", e);
            }
        });
    }


    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            if (dis.readInt() != version) {
                throw new StorageException("Can't read file. Incorrect version");
            }
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // TODO implements sections
            int countSection = dis.readInt();
            for (int i = 0; i < countSection; i++) {
                String sectionName = dis.readUTF(); // имя секции
                String className = dis.readUTF();  // имя класса
                if (className.endsWith("TextSection")) {
                    resume.getSections().put(SectionType.valueOf(sectionName), readTextSection(dis));
                }

                String itemsClassName;
                if (className.endsWith("ListSection")) {
                     itemsClassName = dis.readUTF();
                    if (itemsClassName.equals("String")) {
                        resume.getSections().put(SectionType.valueOf(sectionName), readStringListSection(dis));
                    }
                }

                if (className.endsWith("CompanySection")) {
                    itemsClassName = dis.readUTF();
                    resume.getSections().put(SectionType.valueOf(sectionName),
                            readCompanySection(dis));
                }
            } //for
            return resume;
        }
    }

    private TextSection readTextSection(DataInputStream dis) throws IOException {
        TextSection textSection = new TextSection();
        textSection.setText(dis.readUTF());
        return textSection;
    }

    private ListSection readStringListSection(DataInputStream dis) throws IOException {
        ListSection listSection = new ListSection();
        List<String> stringList = new ArrayList<>();
        int count = dis.readInt();
        for (int i = 0; i < count; i++) {
            stringList.add(dis.readUTF());
        }
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
            List<CompanyPersonalInfo> infoList = new ArrayList();
            for (int j = 0; j < count; j++) {
                CompanyPersonalInfo info = new CompanyPersonalInfo();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate startDate = LocalDate.parse("01/" + dis.readUTF(), format);
                info.setStart(startDate);
                LocalDate endDate = LocalDate.parse("01/" + dis.readUTF(), format);
                info.setEnd(endDate);
                info.setText(dis.readUTF());
                info.setDescription(dis.readUTF());
                infoList.add(info);
            }
            company.setCompanyPersonalInfoList(infoList);
            companyList.add(company);
        }
        companySection.setItems(companyList);
        return companySection;
    }

}
