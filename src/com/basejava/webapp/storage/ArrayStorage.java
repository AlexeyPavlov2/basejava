/**
 * Array based storage for Resumes
 */

package com.basejava.webapp.storage;


import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid = " + uuid + " not found");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -size - 1;   //-(insertion point) - 1 like binarySearch
    }

    @Override
    protected void insert(Resume resume, int index) {
        storage[index] = resume;
    }
}