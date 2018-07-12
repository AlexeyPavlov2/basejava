package com.basejava.webapp.model;

public class Personal extends AbstractOneObjectSection<String> {

    public Personal(String item) {
        super(item);
    }

    @Override
    public void print() {
        System.out.println(item);
    }
}
