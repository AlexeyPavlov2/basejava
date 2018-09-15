package com.basejava.webapp.storage;

public class ObjectStreamPathStorage extends BasePathStorage {
    public ObjectStreamPathStorage(String directory) {
        super(directory, new ObjectStreamFSDriver());
    }
}
