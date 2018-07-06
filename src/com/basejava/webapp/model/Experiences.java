package com.basejava.webapp.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Experiences extends Section {
    private List<ExperienceEntity> entityList = new ArrayList<>();

    public Experiences(List<ExperienceEntity> entityList) {
        this.entityList = entityList;
    }

    public List<ExperienceEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<ExperienceEntity> entityList) {
        this.entityList = entityList;
    }

    @Override
    public void printHTML() {
        System.out.println("<table cellpadding=\"6\" cellspacing=\"6\" width=\"100%\">");
        for(ExperienceEntity el: entityList) {

            System.out.println("<tr>");
            System.out.println("<td colspan=\"2\">");
            System.out.print("<span style=\"color: navy; font-weight: bold; font-size: 16px\">");
            if (!(el.getCompany().getLink() == null) && !el.getCompany().getLink().isEmpty()) {
                System.out.print(
                        "<a href=\"" + el.getCompany().getLink() + "\">" + el.getCompany().getTitle() + "</a>"

                );
            } else {
                System.out.print(el.getCompany().getTitle());
            }
            System.out.println("</span>");

            System.out.println("</td>");
            System.out.println("</tr>");
            System.out.println("<tr>");
            System.out.println("<td width=\"10%\">");
            String d= el.getEnd()==null?"Сейчас" : el.getEnd().format(DateTimeFormatter.ofPattern("MM/uuuu"));
            System.out.println(el.getStart().format(DateTimeFormatter.ofPattern("MM/uuuu")) + " - " + d);
            System.out.println("</td>");
            System.out.println("<td>");
            System.out.print("<span style=\"color: navy; font-weight: bold; font-size: 12px\">" + el.getPosition() + "</span>");
            System.out.println("</td>");
            System.out.println("</tr>");
            System.out.println("<td>");
            System.out.println("</td>");
            System.out.println("<td>");
            System.out.print(el.getDetail());
            System.out.println("</td>");
            System.out.println("<tr>");
        }
        System.out.println("</table>");
    }

    @Override
    public void print() {
        entityList.forEach(System.out::println);
    }
}
