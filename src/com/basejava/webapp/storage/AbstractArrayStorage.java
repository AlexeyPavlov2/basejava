/**
 * Abstract array based storage for Resumes
 */
package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void createElement(Resume resume, Object index) {
        if (isFull()) {
            throw new StorageException("Internal storage is full", resume.getUuid());
        } else {
            insert(resume, (int) index);
            size++;
        }
    }

    @Override
    public Resume readElement(Object index) {
        return storage[(int) index];
    }

    @Override
    public void updateElement(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    @Override
    public void deleteElement(Object index) {
        remove((int) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected boolean isFull() {
        return size == AbstractArrayStorage.STORAGE_LIMIT;
    }

    @Override
    protected boolean isExist(Object index) {
        return (int) index >= 0;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insert(Resume resume, int index);

    protected abstract void remove(int index);

}