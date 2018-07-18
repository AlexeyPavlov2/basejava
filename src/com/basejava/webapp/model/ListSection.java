package com.basejava.webapp.model;

import java.util.List;

public class ListSection<T> extends Section {

    protected List<T> items;

    public ListSection(List<T> items) {
        this.items = items;
    }


    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public void print() {
        items.forEach(System.out::println);
    }
}
