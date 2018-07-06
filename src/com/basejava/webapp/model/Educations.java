package com.basejava.webapp.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Educations extends Section {
    private List<EducationEntity> entityList = new ArrayList<>();

    public Educations(List<EducationEntity> entityList) {
        this.entityList = entityList;
    }

    public List<EducationEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<EducationEntity> entityList) {
        this.entityList = entityList;
    }

    @Override
    public String toString() {
        return "Educations{" +
                "entityList=" + entityList +
                '}';
    }

    @Override
    public void printHTML() {
        System.out.println("<table cellpadding=\"10\" cellspacing=\"10\" width=\"100%\">");
        for (EducationEntity el : entityList) {

            System.out.println("<tr>");
            System.out.println("<td colspan=\"2\">");
            System.out.print("<span style=\"color: navy; font-weight: bold; font-size: 16px\">");
            if (!(el.getSchoolName().getLink() == null) && !el.getSchoolName().getLink().isEmpty()) {
                System.out.print(
                        "<a href=\"" + el.getSchoolName().getLink() + "\">" + el.getSchoolName().getTitle() + "</a>"

                );
            } else {
                System.out.print(el.getSchoolName().getTitle());
            }
            System.out.println("</span>");

            System.out.println("</td>");
            System.out.println("</tr>");

            System.out.println("<tr>");
            System.out.println("<td width=\"10%\">");
            String d = el.getEnd() == null ? "Сейчас" : el.getEnd().format(DateTimeFormatter.ofPattern("MM/uuuu"));
            System.out.println(el.getStart().format(DateTimeFormatter.ofPattern("MM/uuuu")) + " - " + d);
            System.out.println("</td>");
            System.out.println("<td>");
            System.out.print("<span style=\"color: navy; font-weight: bold; font-size: 12px\">" + el.getTitle() + "</span>");
            System.out.println("</td>");
            System.out.println("</tr>");
        }
        System.out.println("</table>");
    }

    @Override
    public void print() {
        entityList.forEach(System.out::println);
    }
}
