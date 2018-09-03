package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection)) return false;
        ListSection<?> that = (ListSection<?>) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {

        return Objects.hash(items);
    }

    @Override
    public void print() {
        items.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "items=" + items +
                '}';
    }
}
