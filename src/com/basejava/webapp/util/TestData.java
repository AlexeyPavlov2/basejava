package com.basejava.webapp.util;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import static com.basejava.webapp.util.Translit.cyrString2Lat;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();
    public static final String UUID_5 = UUID.randomUUID().toString();

    public static final Resume RESUME1 = new Resume(UUID_1, "Сергеева Мария Александровна");
    public static final Resume RESUME2 = new Resume(UUID_2, "Петрова Ольга Владимировна");
    public static final Resume RESUME3 = new Resume(UUID_3, "Иванов Сергей Петрович");
    public static final Resume RESUME4 = new Resume(UUID_4, "Королев Иван Дмитриевич");
    public static final Resume RESUME5 = new Resume(UUID_5, "Аверин Николай Алексеевич");

    public static void fillTestData() {
        RESUME1.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7 (911) 123-45-67");
                    put(ContactType.SKYPE, cyrString2Lat(RESUME1.getFullName()));
                    put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/" + cyrString2Lat(RESUME1.getFullName()));
                    put(ContactType.MAIL, cyrString2Lat(RESUME1.getFullName()) + "@gmail.com");
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
                                " Bison Solution ERP system &#40;Switzerland&#41;. Responsibilities: \n" +
                                        "- Developing and maintaining of business logic modules (like Time management, Production, CRM etc.) in collaboration with Switzerland teams and clients. Key developer for some modules. \n" +
                                        "- Tasks estimation, controlling, testing, demonstration to clients / management. \n" +
                                        "- Technical documentation. \n" +
                                        "- Team lead (about 1.5 year, 4-6 members). \n" +
                                        "Programming languages, etc.: Java, Jython, JUnit; \n" +
                                        "Used servers: JBoss \n" +
                                        "IDE: Eclipse \n" +
                                        "Project mgmt systems, VCS, bug-tracking etc: CVS, IBM Rational Team Concert (RTC), Bugzilla \n" +
                                        "Development organization process: Agile (Scrum) "))),

                new Company(new HyperLink("R1 Company1", "https://r1company2.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2016, 05, 1), LocalDate.of(2017, 10, 01),
                                "Java Junior Developer",
                                "Поддержка работоспособности небольшого офиса:\n" +
                                        "- в рамках настройки рабочих мест использование сценариев WSH-JavaScript;\n" +
                                        "- в рамках настройки серверов использование PERL;\n" +
                                        "- поддержка, настройка рабочих мест (около 50);\n" +
                                        "- поддержка настройка серверов: интернет-шлюз, сервер 1С;\n" +
                                        "- поддержка, настройка офисного оборудования и АТС.")))
        )));

        RESUME1.putSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R1 School1", "https://r1shcool1.com"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 9, 01), LocalDate.of(2018, 05, 01),
                                "R1 Shcool Edu", "")))
        )));


        RESUME2.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7 (912) 456-23-44");
                    put(ContactType.MOBILE, "+7 (926) 854-39-62");
                    put(ContactType.SKYPE, cyrString2Lat(RESUME2.getFullName()));
                    put(ContactType.MAIL, cyrString2Lat(RESUME2.getFullName()) + "@gmail.com");
                }});
        RESUME2.putSection(SectionType.PERSONAL, new TextSection("Открыт в общении с руководством, умею предлагать новые идеи, а также продумывать и внедрять их."));
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
                                "Занималась разработкой Web - приложений для различных систем учета данных(участие более чем в 15-ти проектах).\n" +
                                        "Использовались : Java(6,7,8), Javascript(базовый + библиотеки dojo, jQuery), HTML, CSS, Spring, Vaadin, iBatis, MyBatis, Hibernate, Apache Tomcat , Аpache fop(.pdf, .rtf), Аpache poi(.xls)"),
                                new CompanyPersonalInfo(LocalDate.of(2017, 3, 02),
                                        LocalDate.of(2017, 4, 12), "Java Development Sinior",
                                        "Занималась разработкой Web")
                                )),
                new Company(new HyperLink("Рога и Копыта", "https://roga_i_lopita.com"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2017, 8, 02),
                                LocalDate.of(2017, 9, 17), "Java программист",
                                "Ничо не делал"))),


                new Company(new HyperLink("R2 Company1", "https://r2company2.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2016, 05, 1), LocalDate.of(2017, 10, 01),
                                "Java Developer",
                                "Android application developer \n" +
                                        "IDE: Eclipse \n" +
                                        "Project mgmt systems, VCS, bug-tracking etc: GIT, Redmine")))
        )));

        RESUME2.putSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R2 School1", "https://r2shcool1.com"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 9, 01), LocalDate.of(2018, 05, 01),
                                        "R2 Shcool Edu1 - прикладная математика", ""),
                                new CompanyPersonalInfo(LocalDate.of(2011, 02, 01), LocalDate.of(2013, 04, 01),
                                        "R2 Shcool Edu2 R2 Shcool Edu2 R2 Shcool Edu2 R2 Shcool Edu2 R2 Shcool Edu2 - компьютерное пиратство", "")))
        )));

        RESUME3.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7 (912) 234-45-58");
                    put(ContactType.MAIL, cyrString2Lat(RESUME3.getFullName()) + "@gmail.com");
                    put(ContactType.GITHUB, "http://github.com/" + cyrString2Lat(RESUME3.getFullName()));
                    put(ContactType.HOME_PAGE, "http://homepages.com/" + cyrString2Lat(RESUME3.getFullName()));
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
                                "Обязанности:\n" +
                                        "Разработка Web-интерфейсов с использованием Java, PHP, баз данных MySQL.\n" +
                                        "Достижения:\n" +
                                        "Запуск интернет магазина компании."))),

                new Company(new HyperLink("R3 Company1", "https://r3company2.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2016, 05, 1), LocalDate.of(2017, 10, 01),
                                "C#, C++ Java Developer",
                                "Работа с AS5300 элементами сети, анализ SIP сообщений, выявление причин ошибок и их устранение. Отладка Java кода через Eclipse")))
        )));

        RESUME3.putSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R3 School1", "https://r2shcool1.com"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 9, 01), LocalDate.of(2018, 05, 01),
                                "R2 Shcool Edu1", "")))
        )));


        RESUME4.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7 (926) 990-30-78");
                    put(ContactType.MAIL, cyrString2Lat(RESUME4.getFullName()) + "@gmail.com");
                    put(ContactType.LINKEDIN, "https://linckedin.com/" + cyrString2Lat(RESUME4.getFullName()));
                }});

        RESUME4.putSection(SectionType.PERSONAL, new TextSection(RESUME4.getFullName() + " Personal\n" + "dasasdfadcdakdcasdklbc kjasdkj aljdfakldsfn kljkjndfad lkjskldfadsf laknjsdfkldf alkjdnfn lasjdf sdg trwrfrfwr rgsdg ererrwr rtertwyw wrwrtwrr wrwrtwrt"));
        RESUME4.putSection(SectionType.OBJECTIVE, new TextSection("C# Developer"));
        RESUME4.putSection(SectionType.ACHIEVEMENT, new ListSection<>(Arrays.asList(
                RESUME4.getFullName() + " Achievement1"
        )));
        RESUME4.putSection(SectionType.QUALIFICATIONS, new ListSection<>(Arrays.asList(
                RESUME4.getFullName() + " Qualification1"
        )));

        RESUME4.putSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R4 Company1", null),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2017, 10, 01),
                                CompanyPersonalInfo.FUTURE_DATE, "Kotlin Development",
                                "В данной организации разрабатывал с нуля систему безопасности: разрабатывал архитектуру системы, объектную модель, событийную модель. Разработал взаимодействие ядра Системы с ядром Клиента, разработал классы адаптеров со стороны ядра Системы (объекты данных классов совершают SQL-запросы в БД), полностью разработал ядро Клиента: слой доступа к объектам ядра Системы, слой безопасности (мандатной и дискретной), слой доступа к хранилищу кэшированных объектов, оповещение клиентских компонентов о системных событиях. Знание RMI, JDBC, Swing. Опыт работы с СУБД SQL Server, Postgray")))
        )));

        RESUME4.putSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R4 School1", "https://r4shcool1.com"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 9, 01), LocalDate.of(2018, 05, 01),
                                "R4 Shcool Edu1", "")))
        )));

        RESUME5.setContacts(
                new HashMap<ContactType, String>() {{
                    put(ContactType.PHONE, "+7 (945) 223-49-80");
                    put(ContactType.MOBILE, "+7 (925) 110-07-08");
                    put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/" + cyrString2Lat(RESUME5.getFullName()));
                    put(ContactType.MAIL, cyrString2Lat(RESUME5.getFullName()) + "@microsoft.com");
                    put(ContactType.LINKEDIN, "https://linckedin.com/" + cyrString2Lat(RESUME5.getFullName()));

                }});
        RESUME5.putSection(SectionType.EXPERIENCE, new CompanySection(Arrays.asList(
                new Company(new HyperLink("R5 Company1", "http://r5company1.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2017, 10, 01),
                                CompanyPersonalInfo.FUTURE_DATE, "Java Development",
                                "Основные обязанности - разработка и поддержание функционала web-приложений по заказу ДЖКХиБ города Москвы. \n" +
                                        "Средства: Java core, SQL, JDBC, SpringFramework, Hibernate, Maven, Subversion, JIRA, JasperReports."))),

                new Company(new HyperLink("R5 Company2", "https://r5company2.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2016, 05, 1), LocalDate.of(2017, 10, 01),
                                "C#, Itil master, C++ Java Developer",
                                "Работа 5 ")))
        )));

    }

}
