package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import org.junit.Test;
import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractStorageTest {
    private static final String UUID_00 = "uuid00";
    private static final Resume RESUME00 = new Resume(UUID_00);

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test
    public void checkIsSorted() {
        storage.save(RESUME00);
        Resume[] actual = storage.getAll();
        assertEquals(RESUME00, actual[0]);
    }
}