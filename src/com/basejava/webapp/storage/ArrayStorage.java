/**
 * Array based storage for Resumes
 */

package com.basejava.webapp.storage;


import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private static final int MAX_CAPACITY = 10000;
    private final Resume[] storage = new Resume[MAX_CAPACITY];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size != storage.length) {
            if (getIndex(resume.getUuid()) >= 0) {
                System.out.println("Resume with uuid = " + resume.getUuid() + " already exists.");
            } else {
                storage[size] = resume;
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

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " not exists.");
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid = " + uuid + " not found");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    //Added by AlexeyPavlov methods

    /**
     * Method searches the specified array "storage" of com.basejava.webapp.model.Resume for the specified value of field "uuid"
     *
     * @param uuid - search string
     * @return integer - -1: not found, >=0 - position in the array
     */
    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

}