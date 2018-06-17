package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_4 = "uuid4";

    protected static final Resume RESUME1 = new Resume(UUID_1);
    protected static final Resume RESUME2 = new Resume(UUID_2);
    protected static final Resume RESUME3 = new Resume(UUID_3);
    protected static final Resume RESUME4 = new Resume(UUID_4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
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
    public void getAll() {
        Resume[] actual = storage.getAll();
        assertArrayEquals(new Resume[] {RESUME1, RESUME2, RESUME3}, actual);
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

    @Test(expected = StorageException.class)
    public void saveOverLimit() {
        if (!storage.getClass().getName().endsWith("ListStorage") & !storage.getClass().getName().endsWith("MapStorage")) {
            int count = storage.size();
            try {
                for (int i = count; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                    storage.save(new Resume());
                }
            } catch (Exception e) {
                fail("Exception not expected!");
            }
            storage.save(RESUME4);
        } else {
            throw new StorageException("ListStorage case", "1");
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME4);
    }

    @Test
    public void update() {
        storage.update(RESUME3);
        assertEquals(RESUME3, storage.get(UUID_3));
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