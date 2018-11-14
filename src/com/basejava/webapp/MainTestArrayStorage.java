package com.basejava.webapp;

import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.ArrayStorage;
import com.basejava.webapp.storage.Storage;

public class MainTestArrayStorage {
    public static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Сергеева Мария");
        Resume r2 = new Resume("uuid2", "Петрова Ольга");
        Resume r3 = new Resume("uuid3", "Иванов Сергей");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    public static void printAll() {
        System.out.println("\nGet All");
        if (ARRAY_STORAGE.size() > 0) {
            for (Resume r : ARRAY_STORAGE.getAllSorted()) {
                System.out.println(r);
            }
        } else
            System.out.println("Storage is empty");
    }
}