package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ResumeServlet extends HttpServlet {
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String uuid = request.getParameter("uuid");
        PrintWriter out = response.getWriter();

        StringBuffer responseHTML = new StringBuffer(getPageHead());
        responseHTML.append(getPageHeader()).append("<body>");
        responseHTML.append(getTableHeader());
        if (uuid == null) {
            storage.getAllSorted().forEach(el ->
                    responseHTML.append(getResumeRow(el)));

        } else {
            responseHTML.append(getResumeRow(storage.get(uuid)));
        }

        responseHTML.append("</table>\n");
        responseHTML.append("</body>").append(getPageFooter()).append("</html>");
        out.write(responseHTML.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }


    private String getPageHead() {
        return "<!DOCTYPE html>" + "\n" +
                "<html>" + "\n" +
                "<head>" + "\n" +
                "<meta charset=\"UTF-8\">" + "\n" +
                "<meta charset=\"UTF-8\">" + "\n" +
                "<link rel=\"stylesheet\" href=\"css/style.css\">" + "\n" +
                "<title>Курс JavaSE + Web.</title>" + "\n" +
                "</head>";
    }

    private String getPageHeader() {
        return "<header>Приложение вебинара <a href=\"http://javawebinar.ru/basejava/\" target=\"_blank\">Практика Java. Разработка Web приложения.\"</a></header><br>";
    }

    private String getPageFooter() {
        return "<footer>Приложение вебинара <a href=\"http://javawebinar.ru/basejava/\"" +
                "target=\"_blank\">Практика Java. Разработка Web приложения.\"</a></footer>";
    }

    private String getTableHeader() {
        return "<table>" + "\n" +
                "<tr>" + "\n" +
                "<th>Идентификатор</td>" + "\n" +
                "<th>Полное имя</td>" + "\n" +
                "<th>Контакты</td>" + "\n" +
                "</tr>" + "\n";
    }

    private String getTableColumn(String value) {
        return "<td>" + value + "</td>";
    }

    private String getResumeRow(Resume resume) {
        StringBuffer sb = new StringBuffer();
        sb.append("<tr>\n")
                .append(getTableColumn(resume.getUuid()))
                .append("\n")
                .append(getTableColumn(resume.getFullName()));
        sb.append(getTableColumn(resume.getContacts().entrySet().stream()
                .map(e -> "<p>" + e.getKey().getTitle() + e.getValue() + "</p>").collect(Collectors.joining(""))));
        sb.append("</tr>\n");
        return sb.toString();
    }

}