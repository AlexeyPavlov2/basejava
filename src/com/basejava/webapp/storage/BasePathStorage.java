package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.fsdriver.FileSystemDriver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasePathStorage extends AbstractStorage<Path> {
    private Path directory;
    private FileSystemDriver fileSystemDriver;

    protected BasePathStorage(String dir, FileSystemDriver driver) {
        Objects.requireNonNull(dir, "directory must not be null");
        this.directory = Paths.get(dir);
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        Objects.requireNonNull(driver, "Driver cannot be null");
        this.fileSystemDriver = driver;
    }

    @Override
    public void clear() {
        getFilesName().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesName().count();
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            fileSystemDriver.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create path " + path, resume.getUuid(), e);
        }
        doUpdate(resume, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return fileSystemDriver.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error " + path, e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error " + path, e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        return getFilesName().map(this::doGet).collect(Collectors.toList());
    }

    private Stream<Path> getFilesName() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Path delete error", e);
        }
    }

}