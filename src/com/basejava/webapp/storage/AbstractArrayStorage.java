/**
 * Abstract array based storage for Resumes
 */

package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.List;

import static java.util.Arrays.*;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public final void createElement(Object index, Resume resume) {
        if (isFull()) {
            throw new StorageException("Internal storage is full", resume.getUuid());
        } else {
            insert(resume, (int) index);
            size++;
        }
    }

    @Override
    public final Resume readElement(Object index) {
        return storage[(int) index];
    }

    @Override
    public final void updateElement(Object index, Resume resume) {
        storage[(int) index] = resume;
    }

    @Override
    public final void deleteElement(Object index) {
        remove((int) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public final List<Resume> getAll() {
        return asList(copyOfRange(storage, 0, size));
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    public final void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    protected final boolean isFull() {
        return size == STORAGE_LIMIT;
    }

    @Override
    protected final boolean isExist(Object index) {
        return (int) index > -1;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insert(Resume resume, int index);

    protected abstract void remove(int index);

}