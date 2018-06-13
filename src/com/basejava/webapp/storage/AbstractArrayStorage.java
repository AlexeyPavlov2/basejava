/**
 * Absract array based storage for Resumes
*/

package com.basejava.webapp.storage;


import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10000;
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
        if (size != storage.length) {
            int index = getIndex(resume.getUuid());
            if (index >= 0) {
                System.out.println("Resume with uuid = " + resume.getUuid() + " already exists.");
            } else {
                insert(resume, - index -1);
                size++;
            }
        } else {
            System.out.println("Internal array is full");
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume with uuid " + resume.getUuid() + " not exists.");
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid = " + uuid + " not found");
        } else {
            remove(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " not exists.");
            return null;
        } else {
            return storage[index];
        }
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