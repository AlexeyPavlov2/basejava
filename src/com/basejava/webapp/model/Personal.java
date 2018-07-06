package com.basejava.webapp.model;

public class Personal extends Section {
    private String personal;

    public Personal(String qualities) {
        this.personal = qualities;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }


    @Override
    public void printHTML() {
        System.out.println(personal);

    }

    @Override
    public void print() {
        System.out.println(personal);
    }
}
