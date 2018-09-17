package com.basejava.webapp.storage;

import com.basejava.webapp.storage.fsdriver.ObjectStreamFSDriver;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {
    public ObjectStreamFileStorageTest() {
        super(new BaseFileStorage(STORAGE_DIR, new ObjectStreamFSDriver()));
    }
}
