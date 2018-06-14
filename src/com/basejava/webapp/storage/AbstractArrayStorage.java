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

<<<<<<< HEAD
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume with uuid = " + resume.getUuid() + " not exist");
        } else {
            storage[index] = resume;
=======
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
>>>>>>> testsub
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
<<<<<<< HEAD
        if (index >= 0) {
            System.out.println("Resume with uuid = " + resume.getUuid() + " already exist");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Internal array is full");
=======
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
>>>>>>> testsub
        } else {
            insert(resume, index);
            size++;
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
<<<<<<< HEAD
            System.out.println("Resume with uuid = " + uuid + " not exist");
            return null;
=======
            throw new NotExistStorageException(uuid);
        } else {
            return storage[index];
>>>>>>> testsub
        }
        return storage[index];
    }

<<<<<<< HEAD
=======
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

>>>>>>> testsub
    protected abstract void remove(int index);

    protected abstract void insert(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}