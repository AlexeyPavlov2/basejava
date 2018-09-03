package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.io.File;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    protected File fileHandler;
    protected int size = 0;
    protected FileStorageType storageType;

    public AbstractFileStorage(File fileHandler, FileStorageType storageType) {
        this.fileHandler = fileHandler;
        this.storageType = storageType;
    }

    protected abstract void doWrite(Resume resume);

    protected abstract Resume doRead(Resume resume);


}
