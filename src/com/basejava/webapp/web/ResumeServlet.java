package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.*;
import com.basejava.webapp.storage.SqlStorage;
import com.basejava.webapp.storage.Storage;
import com.basejava.webapp.util.ResumeUtil;
import com.basejava.webapp.util.TestData;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.basejava.webapp.util.DateConverter.StringToLocalDate;
import static com.basejava.webapp.util.TestData.*;

public class ResumeServlet extends HttpServlet {
    private static final long serialVersionUID = 995497791471805151L;
    private final String CLASS_NAME = getClass().getName();
    private final Logger LOG = Logger.getLogger(CLASS_NAME);
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        LOG.info(CLASS_NAME + ": " + " doGet");
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "generate":
                populateDatabase();
                response.sendRedirect("resume");
                return;
            case "download":
            case "send":
                response.sendRedirect("resume");
                return;
            case "insert":
                resume = new Resume("", "");
                ResumeUtil.addAllEmptySection(resume);
                break;
            case "view":
            case "edit":
                resume = storage.get(uuid);
                ResumeUtil.addAllEmptySection(resume);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume resume;
        boolean isNew = false;
        if ("".equals(uuid)) {
            resume = ResumeUtil.getNewResume();
            isNew = true;
        } else {
            resume = storage.get(uuid);
            ResumeUtil.addAllEmptySection(resume);
        }

        // Read data from JSP
        resume.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }

        resume.putSection(SectionType.EXPERIENCE, new CompanySection());
        resume.putSection(SectionType.EDUCATION, new CompanySection());
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            switch (type.name()) {
                case "OBJECTIVE":
                case "PERSONAL":
                    if (value != null) {
                        TextSection section = (TextSection) resume.getSection(type);
                        section.setText(value);
                        resume.putSection(type, section);
                    }
                    break;
                case "ACHIEVEMENT":
                case "QUALIFICATIONS":
                    if (value != null) {
                        List<String> list = new ArrayList<>(Arrays.asList(value.split("\n")));
                        ListSection<String> section = (ListSection<String>) resume.getSection(type);
                        section.setItems(new ArrayList<>(list.stream().filter(el -> el != null && !el.isEmpty() && el.trim().length() != 0).collect(Collectors.toList())));
                        resume.putSection(type, section);
                    }
                    break;
                case "EXPERIENCE":
                case "EDUCATION":
                    if (value != null && values != null && values.length > 0) {

                        List<Company> companies = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "companyURL");

                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            List<CompanyPersonalInfo> positions = new ArrayList<>();
                            //String str =
                            String prefix = type.name() + i;
                            String[] startDates = request.getParameterValues(prefix + "startDate");
                            String[] endDates = request.getParameterValues(prefix + "endDate");
                            for (int k = 0; k < endDates.length; k++) {
                                if ("Сейчас".equals(endDates[k])) {
                                    endDates[k] = "01.12.2050";
                                }
                            }

                            String[] titles = request.getParameterValues(prefix + "text");
                            String[] descriptions = request.getParameterValues(prefix + "description");
                            for (int j = 0; j < titles.length; j++) {
                                LocalDate start = StringToLocalDate(startDates[j]);
                                LocalDate end = StringToLocalDate(endDates[j]);
                                String title = titles[j];
                                String description;
                                if (type == SectionType.EXPERIENCE) {
                                    description = descriptions[j] == null ? "" : descriptions[j];
                                } else {
                                    description = "";
                                }
                                CompanyPersonalInfo info = new CompanyPersonalInfo(start, end, title, description);
                                positions.add(info);
                            }
                            companies.add(new Company(new HyperLink(name, urls[i]), positions));
                        }
                        resume.putSection(type, new CompanySection(companies));
                    }
                    break;
                default:
                    break;
            }  // on SectionType
        }

        // Processing new Company information if exists
        addNewCompany(SectionType.EXPERIENCE, request, resume);
        addNewCompany(SectionType.EDUCATION, request, resume);

        // Save data
        if (isNew) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }

    private void addNewCompany(SectionType sectionType, HttpServletRequest request, Resume resume) {
        String typeName = sectionType.name();
        Map<String, String[]> map = request.getParameterMap();

        if (map.containsKey(typeName + "companyTitle1") &&
                map.containsKey(typeName + "companyURL1") &&
                map.containsKey(typeName + "startDate1") &&
                map.containsKey(typeName + "endDate1") &&
                map.containsKey(typeName + "text1") &&
                !map.get(typeName + "companyTitle1")[0].isEmpty() &&
                !map.get(typeName + "startDate1")[0].isEmpty() &&
                !map.get(typeName + "endDate1")[0].isEmpty() &&
                !map.get(typeName + "text1")[0].isEmpty()
        ) {
            CompanyPersonalInfo position = new CompanyPersonalInfo(StringToLocalDate(map.get(typeName + "startDate1")[0]),
                    StringToLocalDate(map.get(typeName + "endDate1")[0]), map.get(typeName + "text1")[0],
                    sectionType.equals(SectionType.EXPERIENCE) ? map.get(typeName + "description1")[0] : "");
            Company company = new Company(map.get(typeName + "companyTitle1")[0],
                    map.get(typeName + "companyURL1")[0] != null ? map.get(typeName + "companyURL1")[0] : "", position);
            List<Company> companies = new ArrayList<>();
            companies.add(company);
            if (((CompanySection) resume.getSection(sectionType)).getItems() != null) {
                companies.addAll(((CompanySection) resume.getSection(sectionType)).getItems());
            }
            resume.putSection(sectionType, new CompanySection(companies));
        }
    }

    private void printParameterMap(HttpServletRequest request) {
        request.getParameterMap().entrySet().forEach(o -> {
            System.out.println();
            System.out.println("Key: " + o.getKey());
            System.out.println("Value: ");
            Arrays.stream(o.getValue()).forEach(el -> System.out.print(el + " "));
            System.out.println();
        });
    }

    private void populateDatabase() {
        storage.clear();
        TestData.fillTestData();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
        storage.save(RESUME4);
        storage.save(RESUME5);


        System.out.println(Config.getHomeDir() + "\\web\\img\\r1.png");
        ((SqlStorage) storage).setPhoto(RESUME1.getUuid(), Config.getHomeDir() + "\\web\\img\\r1.png");
        ((SqlStorage) storage).setPhoto(RESUME2.getUuid(), Config.getHomeDir() + "\\web\\img\\r2.png");
        ((SqlStorage) storage).setPhoto(RESUME3.getUuid(), Config.getHomeDir() + "\\web\\img\\r3.png");
        ((SqlStorage) storage).setPhoto(RESUME4.getUuid(), Config.getHomeDir() + "\\web\\img\\r4.png");
        ((SqlStorage) storage).setPhoto(RESUME5.getUuid(), Config.getHomeDir() + "\\web\\img\\r5.png");
    }

}