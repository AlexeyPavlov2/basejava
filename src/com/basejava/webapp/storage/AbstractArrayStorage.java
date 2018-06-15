/**
 * Absract array based storage for Resumes
 */

package com.basejava.webapp.storage;


import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size < storage.length) {
            int index = getIndex(resume.getUuid());
            if (index >= 0) {
                throw new ExistStorageException(resume.getUuid());
            } else {
                insert(resume, - index -1);
                size++;
            }
        } else {
            throw new StorageException("Internal array is full", resume.getUuid());
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            remove(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return storage[index];
        }
    }

    public static int getSTORAGE_LIMIT() {
        return STORAGE_LIMIT;
    }

    /**
     * Method searches the specified array "storage" of com.basejava.webapp.model.Resume for the specified value of field "uuid"
     *
     * @param uuid - search string
     * @return integer - -(insertion point) - 1 if not found, >=0 - position in the array for inserting
     */
    protected abstract int getIndex(String uuid);

    /**
     * Method insertes the specified resume in the specified position of array "storage"
     *
     * @param resume - object for inserting
     * @param index  - position in array for inserting
     */
    protected abstract void insert(Resume resume, int index);

    protected abstract void remove(int index);


}