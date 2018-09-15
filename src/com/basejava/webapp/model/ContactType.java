package com.basejava.webapp.model;

public enum ContactType {
    PHONE("Тел. :"),
    SKYPE("Skype:"),
    MAIL("Почта :"),
    LINKEDIN("LinkedIn:"),
    GITHUB("Профиль GitHub:"),
    STACKOVERFLOW("Профиль Stackoverflow :"),
    HOMESITE("Домашняя страница:");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

