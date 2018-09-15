package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

public class MainEquilsTest {
    public static void main(String[] args) {
        Resume resume1 = new Resume("uuid1", "Сергеева Мария");
        resume1.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7(911) 123-4567");
                    put(ContactType.SKYPE, resume1.getFullName());
                    put(ContactType.MAIL, resume1.getFullName() + "@gmail.com");
                }});
        resume1.putSection(SectionType.PERSONAL, new TextSection(resume1.getFullName() + " Personal"));
        resume1.putSection(SectionType.OBJECTIVE, new TextSection("Junior Java Developer"));
        resume1.putSection(SectionType.ACHIEVEMENT, new ListSection<>(Arrays.asList(
                resume1.getFullName() + " Achievement1", resume1.getFullName() + " Achievement2"
        )));
        resume1.putSection(SectionType.QUALIFICATIONS, new ListSection<>(Arrays.asList(
                resume1.getFullName() + " Qualification1", resume1.getFullName() + " Qualification2", resume1.getFullName() + " Qualification3"
        )));
        resume1.putSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R1 Company1", "http://r1company1.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2017, 10, 01),
                                CompanyPersonalInfo.FUTURE_DATE, "Java Junior Development",
                                "Description1"))),

                new Company(new HyperLink("R1 Company1", "https://r1company2.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2016, 05, 1), LocalDate.of(2017, 10, 01),
                                "Java Junior Developer",
                                "Description2")))
        )));

        resume1.putSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R1 School1", "https://r1shcool1.com"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 9, 01), LocalDate.of(2018, 05, 01),
                                "R1 Shcool Edu", "")))
        )));


        Resume resume2 = new Resume("uuid1", "Сергеева Мария");
        resume2.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7(911) 123-4567");
                    put(ContactType.SKYPE, resume2.getFullName());
                    put(ContactType.MAIL, resume2.getFullName() + "@gmail.com");
                }});
        resume2.putSection(SectionType.PERSONAL, new TextSection(resume2.getFullName() + " Personal"));
        resume2.putSection(SectionType.OBJECTIVE, new TextSection("Junior Java Developer"));
        resume2.putSection(SectionType.ACHIEVEMENT, new ListSection<>(Arrays.asList(
                resume2.getFullName() + " Achievement1", resume2.getFullName() + " Achievement2"
        )));
        resume2.putSection(SectionType.QUALIFICATIONS, new ListSection<>(Arrays.asList(
                resume2.getFullName() + " Qualification1", resume2.getFullName() + " Qualification2", resume2.getFullName() + " Qualification3"
        )));
        resume2.putSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R1 Company1", "http://r1company1.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2017, 10, 01),
                                CompanyPersonalInfo.FUTURE_DATE, "Java Junior Development",
                                "Description1"))),

                new Company(new HyperLink("R1 Company1", "https://r1company2.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2016, 05, 1), LocalDate.of(2017, 10, 01),
                                "Java Junior Developer",
                                "Description2")))
        )));

        resume2.putSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R1 School1", "https://r1shcool1.com"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 9, 01), LocalDate.of(2018, 05, 01),
                                "R1 Shcool Edu", "")))
        )));


        System.out.println(resume1.getSection(SectionType.EDUCATION).equals(resume2.getSection(SectionType.EDUCATION)));

        
    }
}
