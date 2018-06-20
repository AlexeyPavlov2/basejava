package com.basejava.webapp.storage;


import com.basejava.webapp.model.Resume;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    public void getAll() {
        Resume[] actualArray = storage.getAll();
        Resume[] expectedArray = new Resume[]{RESUME1, RESUME2, RESUME3};
        Set<Resume> actualSet = new HashSet<Resume>(Arrays.asList(actualArray));
        Set<Resume> expectedSet = new HashSet<Resume>(Arrays.asList(expectedArray));
        assertArrayEquals(actualSet.toArray(), expectedSet.toArray());
    }

}
