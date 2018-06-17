package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    public void createElement(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public Resume readElement(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void updateElement(Resume resume) {
        storage.computeIfPresent(resume.getUuid(), (k, v) -> resume);
    }

    @Override
    public void deleteElement(String uuid) {
        storage.remove(uuid);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        System.out.println("SIZE: " + storage.size());
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
