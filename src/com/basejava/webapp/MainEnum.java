package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

public class MainEnum {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        Phones phones = new Phones(
                new HashMap<String, String>() {{
                    put("Тел.: ", "+7(921) 855-0482");
                    put("Дом. тел.: ", "+7(812) 999-0351");
                }}
        );

        Contacts contacts = new Contacts(Arrays.asList(
                new HyperLink("Skype: ", "grigory.kislin"),
                new HyperLink("Почта: ", "gkislin@yandex.ru"),
                new HyperLink("Профиль LinkedIn: ", "https://www.linkedin.com/in/gkislin"),
                new HyperLink("Профиль GitHub: ", "https://github.com/gkislin"),
                new HyperLink("Профиль Stackoverflow: ", "https://stackoverflow.com/users/548473"),
                new HyperLink("Домашняя страница: ", "http://gkislin.ru/")
        ));

        Objectives objectives = new Objectives(Arrays.asList("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям"));

        Personal personal = new Personal("Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры.");

        Achievements achievements = new Achievements(Arrays.asList(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQue"));

        Qualificatios qualificatios = new Qualificatios(Arrays.asList("1JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce", "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts"));

        Experiences experiences = new Experiences(Arrays.asList(
                new ExperienceEntity(LocalDate.of(2013, 10, 01),
                        null, new HyperLink("Java Online Project", "http://javaops.ru/"), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."),
                new ExperienceEntity(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 01, 01), new HyperLink("Wrike", "https://www.wrike.com/"), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, " +
                        "Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."),
                new ExperienceEntity(LocalDate.of(2012, 04, 01), LocalDate.of(2014, 10, 01), new HyperLink("RIT Center", null),
                        "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python")
        ));

        Educations educations = new Educations(Arrays.asList(
                new EducationEntity(LocalDate.of(2013, 03, 01), LocalDate.of(2013, 05, 01), new HyperLink("Coursera", "https://www.coursera.org/course/progfun") ,
                        "Functional Programming Principles in Scala\" by Martin Odersky"),
                new EducationEntity(LocalDate.of(2011, 03, 01), LocalDate.of(2011, 04, 01), new HyperLink("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"),
                        "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""),
                new EducationEntity(LocalDate.of(2005, 01, 01), LocalDate.of(2005, 04, 01),
                        new HyperLink("Siemens AG", "http://www.siemens.ru/"), "3 месяца обучения мобильным IN сетям (Берлин)")
        ));

        resume.setPhones(phones);
        resume.setContacts(contacts);
        resume.putSection(SectionType.OBJECTIVE, objectives);
        resume.putSection(SectionType.PERSONAL, personal);
        resume.putSection(SectionType.ACHIEVEMENT, achievements);
        resume.putSection(SectionType.QUALIFICATIONS, qualificatios);
        resume.putSection(SectionType.EXPERIENCE, experiences);
        resume.putSection(SectionType.EDUCATION, educations);

        //resume.printHTML();
        resume.print();



    }



}
