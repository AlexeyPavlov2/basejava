package com.basejava.webapp.storage;

import com.basejava.webapp.Config;
import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SqlStorageTest {
    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";

    protected static final Resume RESUME1 = new Resume(UUID_1, "Сергеева Мария");
    protected static final Resume RESUME2 = new Resume(UUID_2, "Петрова Ольга");
    protected static final Resume RESUME3 = new Resume(UUID_3, "Иванов Сергей");
    protected static final Resume RESUME4 = new Resume(UUID_4, "Сидоров Иван");

    public SqlStorageTest() {
        this.storage = new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUser(),
                Config.get().getDbPassword());
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void getAllSorted() {
        List<Resume> expected = Arrays.asList(RESUME1, RESUME2, RESUME3);
        Collections.sort(expected);
        assertEquals(3, storage.getAllSorted().size());
        assertEquals(expected, storage.getAllSorted());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void get() {
        assertEquals(RESUME2, storage.get(UUID_2));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(RESUME2);
    }

    @Test
    public void save() {
        storage.save(RESUME4);
        assertEquals(RESUME4, storage.get(UUID_4));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME4);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        storage.get(UUID_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }


}