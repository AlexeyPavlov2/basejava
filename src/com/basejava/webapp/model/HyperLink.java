package com.basejava.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class HyperLink implements Serializable {
    private static final long serialVersionUID = 6133482848281438648L;
    private String title;
    private String link;

    public HyperLink() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HyperLink)) return false;
        HyperLink hyperLink = (HyperLink) o;
        return Objects.equals(title, hyperLink.title) &&
                Objects.equals(link, hyperLink.link);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, link);
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
