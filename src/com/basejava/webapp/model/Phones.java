package com.basejava.webapp.model;

import java.util.HashMap;
import java.util.Map;

public class Phones extends Section {
    private Map<String, String> phones = new HashMap<>();

    public Phones(Map<String, String> phones) {
        this.phones = phones;
    }

    public Map<String, String> getPhones() {
        return phones;
    }

    public void setPhones(Map<String, String> phones) {
        this.phones = phones;
    }

    @Override
    public void printHTML() {
        phones.forEach((k, v) -> System.out.println(k + v + "<br>" ));
    }

    @Override
    public void print() {
        phones.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
