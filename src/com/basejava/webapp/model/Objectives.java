package com.basejava.webapp.model;

public class Objectives extends AbstractOneObjectSection<String> {

    public Objectives(String item) {
        super(item);
    }

    @Override
    public void print() {
        System.out.println(item);
    }
}
