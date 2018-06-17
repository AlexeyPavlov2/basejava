/**
 * Abstract array based storage for Resumes
 */
package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void createElement(Resume resume) {
        int index = getIndex(resume.getUuid());
        insert(resume, -index - 1);
        size++;
    }

    @Override
    public Resume readElement(String uuid) {
        return storage[getIndex(uuid)];
    }

    @Override
    public void updateElement(Resume resume) {
        storage[getIndex(resume.getUuid())] = resume;
    }

    @Override
    public void deleteElement(String uuid) {
        remove(getIndex(uuid));
        storage[size - 1] = null;
        size--;
    }

    @Override
    public boolean isExist(String uuid) {
        if (!isEmpty()) {
            for (int i = 0; i < size(); i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return true;
                }
            }
        }
        return false;
    }



    /**
     * Method searches the specified array "storage" of com.basejava.webapp.model.Resume for the specified value of field "uuid"
     *
     * @param uuid - search string
     * @return integer - -(insertion point) - 1 if not found, >=0 - position in the array for inserting
     */

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

    public boolean isFull() {
        return size == AbstractArrayStorage.STORAGE_LIMIT;
    }

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