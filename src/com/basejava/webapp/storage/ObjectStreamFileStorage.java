package com.basejava.webapp.storage;

import java.io.File;

public class ObjectStreamFileStorage extends BaseFileStorage {

    public ObjectStreamFileStorage(File directory) {
        super(directory, new ObjectStreamFSDriver());
    }


}
