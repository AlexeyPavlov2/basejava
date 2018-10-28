package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.*;
import com.basejava.webapp.storage.Storage;
import com.basejava.webapp.util.TestData;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
        System.out.println("UUID: " + uuid);
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
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
            case "view":
            case "edit":
                if (uuid.equals("'new'")) {
                    System.out.println("YES");
                    r = new Resume("", "");
                } else {
                    r = storage.get(uuid);
                }
                //System.out.println(r);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume resume = "".equals(uuid) ? new Resume(UUID.randomUUID().toString(), "") : storage.get(uuid);

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

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());

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
                default:
                    break;
            }  // on SectionType
        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String[]> paraMap = new LinkedHashMap<>();


        parameterMap.forEach((key, value) -> {
            if (key.equals("company_title") || key.equals("company_url") ||
                    key.equals("startDate") || key.equals("endDate") || key.equals("position") ||
                    key.equals("description")) {
                paraMap.put(key, value);
            }
        });

        paraMap.forEach((key, value) -> {
            System.out.println("Key: " + key);
            System.out.println("Value: ");
            Arrays.stream(value).forEach(obj -> System.out.println(obj));
            System.out.println();
        });


        // Save data
        if ("".equals(uuid)) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }


    private void populateDatabase() {
        storage.clear();
        TestData.fillTestData();

        storage.save(TestData.RESUME1);
        storage.save(TestData.RESUME2);
        storage.save(TestData.RESUME3);
        storage.save(TestData.RESUME4);
        storage.save(TestData.RESUME5);
    }


}