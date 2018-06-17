/**
 * Abstract class for storage of Resumes
 */
package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        if (isFull()) {
            throw new StorageException("Internal storage is full", resume.getUuid());
        }
        if (isExist(resume.getUuid())) {
            throw new ExistStorageException(resume.getUuid());
        }
        createElement(resume);
    }

    @Override
    public Resume get(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return readElement(uuid);
    }

    @Override
    public void update(Resume resume) {
        if (!isExist(resume.getUuid())) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateElement(resume);
    }

    @Override
    public void delete(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        deleteElement(uuid);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public abstract void createElement(Resume resume);
    public abstract Resume readElement(String uuid);
    public abstract void updateElement(Resume resume);
    public abstract void deleteElement(String uuid);
    public abstract boolean isFull();
    public abstract boolean isExist(String uuid);

}
