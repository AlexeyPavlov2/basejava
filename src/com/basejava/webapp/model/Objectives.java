package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Objectives extends Section {
    private List<String> objList = new ArrayList<>();

    public Objectives(List<String> objList) {
        this.objList = objList;
    }

    public List<String> getObjList() {
        return objList;
    }

    public void setObjList(List<String> objList) {
        this.objList = objList;
    }


    @Override
    public void printHTML() {
        System.out.println("<ul>");
        objList.forEach(e -> System.out.println("<li>" + e + "</li>" ));
        System.out.println("</ul>");
    }

    @Override
    public void print() {
        objList.forEach(System.out::println);
    }
}
