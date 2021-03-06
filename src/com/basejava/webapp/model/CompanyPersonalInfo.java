package com.basejava.webapp.model;

import com.basejava.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyPersonalInfo implements Serializable {
    private static final long serialVersionUID = -4723745829327959451L;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate start;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate end;
    private String text;
    private String description;

    public static final LocalDate FUTURE_DATE = LocalDate.of(2050, 12, 01);

    public CompanyPersonalInfo() {
    }

    public CompanyPersonalInfo(LocalDate start, LocalDate end, String text, String description) {
        this.start = start;
        this.end = end;
        this.text = text;
        this.description = description;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyPersonalInfo)) return false;
        CompanyPersonalInfo that = (CompanyPersonalInfo) o;
        return Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(text, that.text) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, text, description);
    }

    @Override
    public String toString() {
        return "CompanyPersonalInfo{" +
                "start=" + start +
                ", end=" + end +
                ", text='" + text + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
