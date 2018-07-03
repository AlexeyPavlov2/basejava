package com.basejava.webapp.storage;
import com.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insert(int index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void remove(int index) {
        storage[index] = storage[size - 1];
    }
}