package com.basejava.webapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;
    private Phones phones;
    private Contacts contacts;
    Map<SectionType, Section> infoStorage = new HashMap<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void setPhones(Phones phones) {
        this.phones = phones;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public void putSection(SectionType sectionType, Section section) {
        infoStorage.put(sectionType, section);
    }

    public Section getSection(SectionType sectionType) {
        return infoStorage.get(sectionType);
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);

    }

    public String getFullName() {
        return fullName;
    }

    public Map<SectionType, Section> getInfoStorage() {
        return infoStorage;
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }


    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }

    public void printHTML() {
        System.out.print("<span style=\"color: black; font-weight: bold; font-size: 20px\">" +
                fullName + "</span><br><br>");

        phones.printHTML();
        contacts.printHTML();

        infoStorage.forEach((k,v) -> {
            if (v != null) {
                System.out.println("<br>" + "<span style=\"color: black; font-weight: bold; font-size: 18px\">" +
                        k.getTitle() + "</span><br>");
                v.printHTML();
                System.out.println("<br>");
            }
        });

    }

    public void print() {
        System.out.println(fullName);
        phones.print();
        contacts.print();
        infoStorage.forEach((k, v) -> {
            if (v != null) {
                System.out.println(k.getTitle());
                v.print();
            }
        });
    }

}