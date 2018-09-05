package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
            Arrays.stream(directory.listFiles()).filter(el -> el != null & el.isFile()).forEach(el -> el.delete());
    }

    @Override
    public int size() {
        return (int) Arrays.stream(directory.listFiles()).filter(el -> el.isFile()).count();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("Can't update resume " + resume.getUuid(), resume.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("Can't save resume " + resume.getUuid(), file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("Can't read file " + file.getName(), file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if(file != null && !file.delete()) {
            throw new StorageException("Can't delete file " + file.getName(), file.getName());
        };
    }

    @Override
    protected List<Resume> doCopyAll() {
        return Arrays.asList(directory.listFiles())
                .stream()
                .map(el -> doGet(el))
                .collect(Collectors.toList());
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;
    protected abstract Resume doRead(File file) throws IOException;
}