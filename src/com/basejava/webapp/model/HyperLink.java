package com.basejava.webapp.model;

import java.io.Serializable;

public class HyperLink implements Serializable {
    private static final long serialVersionUID = 6133482848281438648L;
    private String title;
    private String link;

    public HyperLink(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public void printHTML(boolean lineBreak) {
        String forPrint = "";
        if (title.indexOf("Skype") != -1) {
            forPrint = title + "<a href=\"skype:" + link + "\">" + link + "</a>";
        } else if (link.indexOf("@") != -1) {
            forPrint = title + "<a href=\"mailto:" + link + "\">" + link + "</a>";
        } else {
            forPrint = title + "<a href=\"" + link + "\">" + link + "</a>";
        }

        if (lineBreak) {
            forPrint += "<br>";
        }
        System.out.println(forPrint);
    }

    public void print() {
        String ref = link == null ? "Ссылка отсутствует" : link;
        System.out.println(title + "  :   " + ref);
    }

    @Override
    public String toString() {

        return "title='" + title + '\'' + ", link='" + link + '\'';
    }
}
