package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void createElement(Object searchKey, Resume resume) {
        storage.add(resume);
    }

    @Override
    public Resume readElement(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    public void updateElement(Object index, Resume resume) {
        storage.set((int) index, resume);
    }

    @Override
    public void deleteElement(Object index) {
        storage.remove((int) index);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }
}