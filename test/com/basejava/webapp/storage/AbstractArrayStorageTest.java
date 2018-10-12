package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Test;

import static com.basejava.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static com.basejava.webapp.util.TestData.RESUME4;
import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverLimit() {
        int count = storage.size();
        try {
            for (int i = count; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("fullName" + i));
            }
        } catch (Exception e) {
            fail("Exception not expected!");
        }
        storage.save(RESUME4);
    }
}