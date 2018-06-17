package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void createElement(Resume resume) {
        storage.add(resume);
    }

    @Override
    public Resume readElement(String uuid) {
        for(Resume el : storage) {
            if (el.getUuid().equals(uuid)) {
                return el;
            }
        }
        return null;
    }

    @Override
    public void updateElement(Resume resume) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(resume.getUuid())) {
                storage.set(i, resume);
                break;
            }
        }
    }

    @Override
    public void deleteElement(String uuid) {
        storage.removeIf(el -> el.getUuid().equals(uuid));
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isExist(String uuid) {
        for (Resume el : storage) {
            if (el.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void clear() {
        storage.clear();
    }



}
