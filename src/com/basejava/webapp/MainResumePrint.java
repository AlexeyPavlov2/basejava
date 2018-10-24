package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainResumePrint {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        Map<ContactType, String> contacts = new HashMap<ContactType, String>() {{
            put(ContactType.PHONE, "+7(921) 855-0482");
            put(ContactType.SKYPE, "grigory.kislin");
            put(ContactType.MAIL, "gkislin@yandex.ru");
            put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
            put(ContactType.GITHUB, "https://github.com/gkislin");
            put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
            put(ContactType.HOME_PAGE, "http://gkislin.ru/");
        }};

        Section achievements = new ListSection<>(Arrays.asList(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQue",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."
                ));

        TextSection objectives = new TextSection("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям");

        TextSection personal = new TextSection("Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры.");

        Section qualificatios = new ListSection<>(Arrays.asList("1JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce", "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
                "Python: Django.",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов",
                "проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\""
        ));

        CompanySection companiesEducation = new CompanySection(Arrays.asList(
                new Company(new HyperLink("Coursera", "https://www.coursera.org/course/progfun"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 03, 01), LocalDate.of(2013, 05, 01),
                                "Functional Programming Principles in Scala\" by Martin Odersky", ""))),

                new Company(new HyperLink("Luxoft (Deutsche Bank)", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2011, 03, 01), LocalDate.of(2011, 04, 01),
                                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", ""))),

                new Company(new HyperLink("Siemens AG", "http://www.siemens.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2005, 01, 01), LocalDate.of(2005, 04, 01),
                                "3 месяца обучения мобильным IN сетям (Берлин)", ""))),

                new Company(new HyperLink("Alcatel", "http://www.alcatel.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(1997, 9, 01), LocalDate.of(1998, 03, 01),
                                "6 месяцев обучения цифровым телефонным сетям (Москва)", ""))),

                new Company(new HyperLink("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(1993, 03, 01), LocalDate.of(1996, 07, 01),
                                "Аспирантура (программист С, С++)", ""),
                        new CompanyPersonalInfo(LocalDate.of(1987, 9, 01), LocalDate.of(1993, 07, 01),
                                "Инженер (программист Fortran, C)", ""))),
                new Company(new HyperLink("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(1984, 9, 01), LocalDate.of(1987, 06, 01),
                                "Закончил с отличием", "")))

        ));

        CompanySection companiesExperiens = new CompanySection(Arrays.asList(
                new Company(new HyperLink("Java Online Project", "http://javaops.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2013, 10, 01),
                        CompanyPersonalInfo.FUTURE_DATE, "Автор проекта.",
                                        "Создание, организация и проведение Java онлайн проектов и стажировок."))),

                new Company(new HyperLink("Wrike", "https://www.wrike.com/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 01, 01),
                        "Старший разработчик (backend)",
                                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, " +
                        "Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))),

                new Company(new HyperLink("RIT Center", null),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2012, 04, 01), LocalDate.of(2014, 10, 01),
                        "Java архитектор",
                                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"))),
                new Company(new HyperLink("Yota", "https://www.yota.ru/"),
                        Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2010, 12, 01), LocalDate.of(2012, 04, 01),
                                "Ведущий программист",
                                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"))),
        new Company(new HyperLink("Enkata", "http://enkata.com/"),
                Arrays.asList(new CompanyPersonalInfo(LocalDate.of(2007, 03, 01), LocalDate.of(2008, 06, 01),
                        "Разработчик ПО",
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).")))

        ));

        resume.setContacts(contacts);
        resume.putSection(SectionType.OBJECTIVE, objectives);
        resume.putSection(SectionType.PERSONAL, personal);
        resume.putSection(SectionType.ACHIEVEMENT, achievements);
        resume.putSection(SectionType.QUALIFICATIONS, qualificatios);
        resume.putSection(SectionType.EXPERIENCE, companiesExperiens);
        resume.putSection(SectionType.EDUCATION, companiesEducation);

        resume.print();

    }


}
