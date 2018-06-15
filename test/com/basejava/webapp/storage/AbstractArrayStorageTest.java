package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME1 = new Resume(UUID_1);
    private static final Resume RESUME2 = new Resume(UUID_2);
    private static final Resume RESUME3 = new Resume(UUID_3);
    private static final Resume RESUME4 = new Resume(UUID_4);


    public AbstractArrayStorageTest(Storage storage) {
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
        int count = storage.size();
        try {
            for (int i = count; i < AbstractArrayStorage.getSTORAGE_LIMIT(); i++) {
                storage.save(new Resume("uuidt" + String.valueOf(i)));
            }
        } catch (Exception e) {
            fail("Exception not expected!");
        }
        storage.save(RESUME4);
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