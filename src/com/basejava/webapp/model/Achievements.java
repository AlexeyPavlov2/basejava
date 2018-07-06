package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Achievements extends Section {
    private List<String> achievList = new ArrayList<>();

    public Achievements(List<String> achievList) {
        this.achievList = achievList;
    }

    public List<String> getAchievList() {
        return achievList;
    }

    public void setAchievList(List<String> achievList) {
        this.achievList = achievList;
    }

    @Override
    public void printHTML() {
        System.out.println("<ul>");
        achievList.forEach(e -> System.out.println("<li>" + e + "</li>" ));
        System.out.println("</ul>");

    }

    @Override
    public void print() {
        achievList.forEach(System.out::println);
    }
}
