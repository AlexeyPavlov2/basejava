package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeCollection(dos, resume.getContacts().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeCollection(dos, resume.getSections().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name()); // имя секции
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) entry.getValue()).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListSection<String>) entry.getValue()).getItems(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((CompanySection) entry.getValue()).getItems(),
                                company -> {
                                    dos.writeUTF(company.getLink().getTitle());
                                    dos.writeUTF(company.getLink().getLink());
                                    writeCollection(dos, company.getCompanyPersonalInfoList(), info -> {
                                        dos.writeUTF(info.getStart().format(format));
                                        dos.writeUTF(info.getEnd().format(format));
                                        dos.writeUTF(info.getText());
                                        dos.writeUTF(info.getDescription() == null ? "" : info.getDescription());
                                    });
                                }
                        );
                        break;
                    default:
                        throw new StorageException("Unknown SectionType name " + entry.getKey().name());
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            resume.setContacts(readContacts(dis));

            readCustomCollection(dis, () -> {
                String sectionName = dis.readUTF(); // имя секции
                switch (sectionName) {
                    case "PERSONAL":
                    case "OBJECTIVE":
                        resume.getSections().put(SectionType.valueOf(sectionName), new TextSection(dis.readUTF()));
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        resume.getSections().put(SectionType.valueOf(sectionName),
                                new ListSection<String>(readList(dis, dis::readUTF)));
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        resume.getSections().put(SectionType.valueOf(sectionName),
                                readCompanySection(dis));
                        break;
                    default:
                        throw new StorageException("Unknown SectionType name " + sectionName);
                }
            });
            return resume;
        }
    }

    private Map<ContactType, String> readContacts(DataInputStream in) throws IOException {
        Map<ContactType, String> map = new HashMap<>();
        readCustomCollection(in, () -> map.put(ContactType.valueOf(in.readUTF()), in.readUTF()));
        return map;
    }

    private CompanySection readCompanySection(DataInputStream dis) throws IOException {
        return new CompanySection(readList(dis, () ->
                new Company(new HyperLink(dis.readUTF(), dis.readUTF()),
                        readList(dis, () ->
                                new CompanyPersonalInfo(LocalDate.parse(dis.readUTF(), format),
                                        LocalDate.parse(dis.readUTF(), format),
                                        dis.readUTF(), dis.readUTF())
                        ))
        ));
    }

    interface ItemCustomReader {
        void read() throws IOException;
    }

    interface ItemReader<T> {
        T read() throws IOException;
    }

    interface ItemWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> List<T> readList(DataInputStream in, ItemReader<T> reader) throws IOException {
        List<T> list = new ArrayList<T>();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            list.add((T) reader.read());
        }
        return list;
    }

    private <T> void writeCollection(DataOutputStream out, Collection<T> items, ItemWriter<T> writer) throws IOException {
        out.writeInt(items.size());
        for (T item : items) {
            writer.write(item);
        }
    }

    private void readCustomCollection(DataInputStream in, ItemCustomReader reader) throws IOException {
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            reader.read();
        }
    }


}
