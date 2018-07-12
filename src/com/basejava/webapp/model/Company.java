package com.basejava.webapp.model;

import java.time.LocalDate;

public class Company {

    private HyperLink link;
    private LocalDate start;
    private LocalDate end;
    private String text;
    private String description;

    public Company(HyperLink link, LocalDate start, LocalDate end, String text, String description) {
        this.link = link;
        this.start = start;
        this.end = end;
        this.text = text;
        this.description = description;
    }

    public HyperLink getLink() {
        return link;
    }

    public void setLink(HyperLink link) {
        this.link = link;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Company{" +
                "link=" + link +
                ", start=" + start +
                ", end=" + end +
                ", text='" + text + '\'' +
                ", description='" + description + '\'' +
                '}';
    }




}
