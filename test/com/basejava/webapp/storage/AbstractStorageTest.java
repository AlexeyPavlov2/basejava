package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("D:\\git\\basejava\\storage");
    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";

    protected static final Resume RESUME1 = new Resume(UUID_1, "Сергеева Мария");
    protected static final Resume RESUME2 = new Resume(UUID_2, "Петрова Ольга");
    protected static final Resume RESUME3 = new Resume(UUID_3, "Иванов Сергей");
    protected static final Resume RESUME4 = new Resume(UUID_4, "Сидоров Иван");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeClass
    public static void initClass() {
        fillTestData();
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void getAllSorted() {
        List<Resume> expected = Arrays.asList(RESUME1, RESUME2, RESUME3);
        Collections.sort(expected);
        assertEquals(expected, storage.getAllSorted());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void get() {
        assertEquals(RESUME2, storage.get(UUID_2));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(RESUME2);
    }

    @Test
    public void save() {
        storage.save(RESUME4);
        assertEquals(RESUME4, storage.get(UUID_4));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME4);
    }

    @Test
    public void update() {
        /*storage.update(RESUME3);
        assertEquals(RESUME3, storage.get(UUID_3));*/
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        storage.get(UUID_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

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
        RESUME1.putSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(
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
        )));


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
        RESUME2.putSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(
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
        )));

        RESUME3.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7(912) 456-234");
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
        RESUME3.putSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(
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
        )));


        RESUME4.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7(912) 456-234");
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
        RESUME4.putSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R4 Company1", "http://r4company1.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2017, 10, 01),
                                CompanyPersonalInfo.FUTURE_DATE, "Kotlin Development",
                                "Description1")))
        )));

        RESUME4.putSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R4 School1", "https://r4shcool1.com"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 9, 01), LocalDate.of(2018, 05, 01),
                                "R4 Shcool Edu1", "")))
        )));
    }


}