package com.basejava.webapp.model;

import java.time.LocalDate;

public class EducationEntity {
    private LocalDate start;
    private LocalDate end;
    private HyperLink schoolName;
    private String title;

    public EducationEntity(LocalDate start, LocalDate end, HyperLink schoolName, String title) {
        this.start = start;
        this.end = end;
        this.schoolName = schoolName;
        this.title = title;
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

    public HyperLink getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(HyperLink schoolName) {
        this.schoolName = schoolName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return  "start=" + start +
                ", end=" + end +
                ", schoolName='" + schoolName + '\'' +
                ", title='" + title + '\'';
    }
}
