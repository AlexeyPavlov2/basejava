/**
 * List<> based storage for Resumes
 */
package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void createElement(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    public Resume readElement(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    public void updateElement(Resume resume, Object index) {
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
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public List<Resume> getAllSorted() {
        return storage.stream()
                .sorted(Comparator.comparing(Resume::getFullName))
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
