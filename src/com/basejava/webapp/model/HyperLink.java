package com.basejava.webapp.model;

public class HyperLink {

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
        if (title.indexOf("Skype")!= -1) {
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
        System.out.println(title + " : " + link);
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' + ", link='" + link + '\'';
    }
}
