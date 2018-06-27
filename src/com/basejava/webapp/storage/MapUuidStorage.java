/**
 * Map<> based storage for Resumes
 */

package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    public void createElement(Object searchKey, Resume resume) {
        storage.put((String) searchKey, resume);
    }

    @Override
    public Resume readElement(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    public void updateElement(Object searchKey, Resume resume) {
        storage.put((String) searchKey, resume);
    }

    @Override
    public void deleteElement(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}