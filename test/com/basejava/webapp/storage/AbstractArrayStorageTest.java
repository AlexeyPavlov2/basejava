package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Test;
import static com.basejava.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.fail;

public class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverLimit() {
        int count = storage.size();
        try {
            for (int i = count; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            fail("Exception not expected!");
        }
        storage.save(RESUME4);
    }
}