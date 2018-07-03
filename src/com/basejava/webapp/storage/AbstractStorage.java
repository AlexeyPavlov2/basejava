package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.List;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void save(Resume resume) {
        Object searchKey = getIfNotExist(resume.getUuid());
        createElement(searchKey, resume);
    }

    @Override
    public final Resume get(String uuid) {
        Object searchKey = getIfExist(uuid);
        return readElement(searchKey);
    }

    @Override
    public final void update(Resume resume) {
        Object searchKey = getIfExist(resume.getUuid());
        updateElement(searchKey, resume);
    }

    @Override
    public final void delete(String uuid) {
        Object searchKey = getIfExist(uuid);
        deleteElement(searchKey);
    }

    @Override
    public final List<Resume> getAllSorted() {
        List<Resume> list = getAll();
        list.sort(Resume::compareTo);
        return list;
    }

    private final Object getIfExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private final Object getIfNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }


    public abstract void createElement(Object searchKey, Resume resume);

    public abstract Resume readElement(Object searchKey);

    public abstract void updateElement(Object searchKey, Resume resume);

    public abstract void deleteElement(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract List<Resume> getAll();

    protected abstract Object getSearchKey(String uuid);


}