/**
 * Sorted array based storage for Resumes
 */

package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage  {

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid = " + uuid + " not found");
        } else {
            System.arraycopy(storage, index+ 1, storage, index, size - index - 1);
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        int index = Arrays.binarySearch(storage, 0, size, resume);
        return index;
    }

    @Override
    protected void insert(Resume resume, int index) {
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }



}
