package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import org.junit.Test;
import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}