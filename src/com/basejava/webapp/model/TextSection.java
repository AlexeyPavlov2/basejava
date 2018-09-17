package com.basejava.webapp.model;

import java.util.Objects;

public class TextSection extends Section {
    private static final long serialVersionUID = -2700503340512386762L;
    private String text;

    public TextSection() {
    }

    public TextSection(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextSection)) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public void print() {
        System.out.println(text);
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "text='" + text + '\'' +
                '}';
    }
}
