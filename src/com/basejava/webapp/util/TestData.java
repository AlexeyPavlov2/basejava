package com.basejava.webapp.util;

import com.basejava.webapp.model.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class TestData {

    /*public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String UUID_4 = "uuid4";*/
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume RESUME1 = new Resume(UUID_1, "Сергеева Мария");
    public static final Resume RESUME2 = new Resume(UUID_2, "Петрова Ольга");
    public static final Resume RESUME3 = new Resume(UUID_3, "Иванов Сергей");
    public static final Resume RESUME4 = new Resume(UUID_4, "Сидоров Иван");

    public static void fillTestData() {
        RESUME1.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7(911) 123-4567");
                    put(ContactType.SKYPE, RESUME1.getFullName());
                    put(ContactType.MAIL, RESUME1.getFullName() + "@gmail.com");
                }});
        RESUME1.putSection(SectionType.PERSONAL, new TextSection(RESUME1.getFullName() + " Personal"));
        RESUME1.putSection(SectionType.OBJECTIVE, new TextSection("Junior Java Developer"));
        RESUME1.putSection(SectionType.ACHIEVEMENT, new ListSection<>(Arrays.asList(
                RESUME1.getFullName() + " Achievement1", RESUME1.getFullName() + " Achievement2"
        )));
        RESUME1.putSection(SectionType.QUALIFICATIONS, new ListSection<>(Arrays.asList(
                RESUME1.getFullName() + " Qualification1", RESUME1.getFullName() + " Qualification2", RESUME1.getFullName() + " Qualification3"
        )));

        /*RESUME1.putSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R1 Company1", "http://r1company1.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2017, 10, 01),
                                CompanyPersonalInfo.FUTURE_DATE, "Java Junior Development",
                                "Description1"))),

                new Company(new HyperLink("R1 Company1", "https://r1company2.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2016, 05, 1), LocalDate.of(2017, 10, 01),
                                "Java Junior Developer",
                                "Description2")))
        )));

        RESUME1.putSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R1 School1", "https://r1shcool1.com"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 9, 01), LocalDate.of(2018, 05, 01),
                                "R1 Shcool Edu", "")))
        )));*/


        RESUME2.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7(912) 456-234");
                    put(ContactType.MAIL, RESUME2.getFullName() + "@gmail.com");
                }});
        RESUME2.putSection(SectionType.PERSONAL, new TextSection(RESUME2.getFullName() + " Personal"));
        RESUME2.putSection(SectionType.OBJECTIVE, new TextSection("Java Developer"));
        RESUME2.putSection(SectionType.ACHIEVEMENT, new ListSection<>(Arrays.asList(
                RESUME2.getFullName() + " Achievement1", RESUME2.getFullName() + " Achievement2"
        )));
        RESUME2.putSection(SectionType.QUALIFICATIONS, new ListSection<>(Arrays.asList(
                RESUME2.getFullName() + " Qualification1", RESUME2.getFullName() + " Qualification2"
        )));

        /*RESUME2.putSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R2 Company1", "http://r2company1.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2017, 10, 01),
                                CompanyPersonalInfo.FUTURE_DATE, "Java Development",
                                "Description1"))),

                new Company(new HyperLink("R2 Company1", "https://r2company2.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2016, 05, 1), LocalDate.of(2017, 10, 01),
                                "Java Developer",
                                "Description2")))
        )));

        RESUME2.putSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R2 School1", "https://r2shcool1.com"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 9, 01), LocalDate.of(2018, 05, 01),
                                        "R2 Shcool Edu1", ""),
                                new CompanyPersonalInfo(LocalDate.of(2011, 02, 01), LocalDate.of(2013, 04, 01),
                                        "R2 Shcool Edu2", "")))
        )));*/

        RESUME3.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7(912) 234-458");
                    put(ContactType.MAIL, RESUME3.getFullName() + "@gmail.com");
                    put(ContactType.GITHUB, "http://github.com/" + RESUME3.getFullName());
                }});

        RESUME3.putSection(SectionType.PERSONAL, new TextSection(RESUME3.getFullName() + " Personal"));
        RESUME3.putSection(SectionType.OBJECTIVE, new TextSection("C# Developer"));
        RESUME3.putSection(SectionType.ACHIEVEMENT, new ListSection<>(Arrays.asList(
                RESUME3.getFullName() + " Achievement1", RESUME3.getFullName() + " Achievement2"
        )));
        RESUME3.putSection(SectionType.QUALIFICATIONS, new ListSection<>(Arrays.asList(
                RESUME3.getFullName() + " Qualification1", RESUME3.getFullName() + " Qualification2"
        )));

        /*RESUME3.putSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R3 Company1", "http://r3company1.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2017, 10, 01),
                                CompanyPersonalInfo.FUTURE_DATE, "Java Development",
                                "Description1"))),

                new Company(new HyperLink("R3 Company1", "https://r3company2.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2016, 05, 1), LocalDate.of(2017, 10, 01),
                                "C#, C++ Java Developer",
                                "Description2")))
        )));

        RESUME3.putSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R3 School1", "https://r2shcool1.com"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 9, 01), LocalDate.of(2018, 05, 01),
                                "R2 Shcool Edu1", "")))
        )));*/


        RESUME4.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7(926) 990-308");
                    put(ContactType.MAIL, RESUME4.getFullName() + "@gmail.com");
                    put(ContactType.LINKEDIN, RESUME4.getFullName());
                }});

        RESUME4.putSection(SectionType.PERSONAL, new TextSection(RESUME4.getFullName() + " Personal"));
        RESUME4.putSection(SectionType.OBJECTIVE, new TextSection("C# Developer"));
        RESUME4.putSection(SectionType.ACHIEVEMENT, new ListSection<>(Arrays.asList(
                RESUME4.getFullName() + " Achievement1"
        )));
        RESUME4.putSection(SectionType.QUALIFICATIONS, new ListSection<>(Arrays.asList(
                RESUME4.getFullName() + " Qualification1"
        )));

        /*RESUME4.putSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R4 Company1", "http://r4company1.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2017, 10, 01),
                                CompanyPersonalInfo.FUTURE_DATE, "Kotlin Development",
                                "Description1")))
        )));

        RESUME4.putSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R4 School1", "https://r4shcool1.com"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 9, 01), LocalDate.of(2018, 05, 01),
                                "R4 Shcool Edu1", "")))
        )));*/
    }

}
