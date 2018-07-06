package com.basejava.webapp.model;

import java.time.LocalDate;

public class ExperienceEntity {
    private LocalDate start;
    private LocalDate end;
    private HyperLink company;
    private String position;
    private String detail;

    public ExperienceEntity(LocalDate start, LocalDate end, HyperLink company, String position, String detail) {
        this.start = start;
        this.end = end;
        this.company = company;
        this.position = position;
        this.detail = detail;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public HyperLink getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        return  "start=" + start +
                ", end=" + end +
                ", company=" + company +
                ", position='" + position + '\'' +
                ", detail='" + detail + '\'';

    }
}
