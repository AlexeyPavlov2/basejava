package com.basejava.webapp.storage;

import com.basejava.webapp.storage.fsdriver.ObjectStreamFSDriver;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new BasePathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamFSDriver()));
    }
}
