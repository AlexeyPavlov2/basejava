package com.basejava.webapp.model;

import java.util.List;

public class Qualificatios extends AbstractObjectListSection<String> {

    public Qualificatios(List<String> items) {
        super(items);
    }

    @Override
    public void print() {
        items.forEach(System.out::println);
    }
}
