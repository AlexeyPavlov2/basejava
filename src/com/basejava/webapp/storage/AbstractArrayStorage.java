/**
 * Absract array based storage for Resumes
*/

package com.basejava.webapp.storage;


import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume with uuid = " + resume.getUuid() + " not exist");
        } else {
            storage[index] = resume;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Resume with uuid = " + resume.getUuid() + " already exist");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Internal array is full");
        } else {
            insert(resume, index);
            size++;
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
            System.out.println("Resume with uuid = " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract void remove(int index);

    protected abstract void insert(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}