package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Contacts extends Section {
    private List<HyperLink> contactList = new ArrayList<>();

    public Contacts(List<HyperLink> contactList) {
        this.contactList = contactList;
    }

    public List<HyperLink> getContactList() {
        return contactList;
    }


    @Override
    public void printHTML() {
        contactList.forEach(e -> e.printHTML(true));
    }

    @Override
    public void print() {
        contactList.forEach(System.out::println);
    }
}
