package com.basejava.webapp.model;

public abstract class AbstractOneObjectSection<T> extends Section {

    protected T item;

    public AbstractOneObjectSection(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
