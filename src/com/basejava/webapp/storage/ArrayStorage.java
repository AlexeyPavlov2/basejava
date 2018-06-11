/**
 * Array based storage for Resumes
 */

package com.basejava.webapp.storage;


import com.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

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

    @Override
    protected void fillHole(int index) {
        storage[index] = storage[size - 1];
    }
}