package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Qualificatios extends Section {
    private List<String> quaStrings = new ArrayList<>();

    public Qualificatios(List<String> quaStrings) {
        this.quaStrings = quaStrings;
    }

    public List<String> getQuaStrings() {
        return quaStrings;
    }

    public void setQuaStrings(List<String> quaStrings) {
        this.quaStrings = quaStrings;
    }

    @Override
    public void printHTML() {
        System.out.println("<ul>");
        quaStrings.forEach(e -> System.out.println("<li>" + e + "</li>" ));
        System.out.println("</ul>");
    }

    @Override
    public void print() {
        quaStrings.forEach(System.out::println);
    }
}
