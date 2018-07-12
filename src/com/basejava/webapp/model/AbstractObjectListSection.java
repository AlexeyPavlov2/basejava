package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractObjectListSection<T> extends Section {

    protected List<T> items = new ArrayList<>();

    public AbstractObjectListSection(List<T> items) {
        this.items = items;
    }


    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
