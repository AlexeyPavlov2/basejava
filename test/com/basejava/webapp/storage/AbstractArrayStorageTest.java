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

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] expected = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Resume[] actual = storage.getAll();
        StringBuilder sbExpected = new StringBuilder();
        StringBuilder sbActual = new StringBuilder();
        for (Resume resume : expected)
            sbExpected.append(resume);
        for (Resume resume : actual)
            sbActual.append(resume);
        assertArrayEquals(sbExpected.toString().getBytes(), sbActual.toString().getBytes());
    }


    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void get() {
        assertEquals("uuid2", storage.get("uuid2").getUuid());
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(new Resume("uuid2"));
    }

    @Test
    public void save() {
        storage.save(new Resume("uuid4"));
        assertTrue("uuid4".equals(storage.get("uuid4").getUuid()));
    }

    @Test(expected = StorageException.class)
    public void saveOverLimit() {
        int count = storage.size();
        for (int i = count; i < AbstractArrayStorage.getSTORAGE_LIMIT() + 1; i++ ) {
            storage.save(new Resume("uuidt" + String.valueOf(i)));
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("uuid8"));
    }

    @Test   //????????????
    public void update() {
        storage.update(new Resume("uuid3"));
        assertTrue("uuid3".equals(storage.get("uuid3").getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid8");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid3");
        storage.get("uuid3");
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }


}