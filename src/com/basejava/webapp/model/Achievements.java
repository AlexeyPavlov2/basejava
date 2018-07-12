package com.basejava.webapp.model;

import java.util.List;

public class Achievements extends AbstractObjectListSection<String> {

    public Achievements(List<String> items) {
        super(items);
    }

    @Override
    public void print() {
        items.forEach(System.out::println);
    }
}
