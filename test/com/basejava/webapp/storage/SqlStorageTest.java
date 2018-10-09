package com.basejava.webapp.storage;

import com.basejava.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest(Storage storage) {
        super(new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUser(),
                Config.get().getDbPassword()));
    }

}