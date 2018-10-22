package com.basejava.webapp;

import com.basejava.webapp.storage.SqlStorage;
import com.basejava.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final String PROPERTIES_FILE= "config\\resumes.properties";
    private static final String ABS_PATH_FILE = "D:\\git\\basejava\\config\\resumes.properties";
    private static final File PROPS = new File(PROPERTIES_FILE);
    private static final Config INSTANCE = new Config();

    private final File storageDir;
    private final Storage storage;
    private String dbDriver;

    private Config() {
        try ( InputStream is = new FileInputStream(ABS_PATH_FILE)) {
            Properties props = new Properties();
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            dbDriver = props.getProperty("db.driver");
            storage = new SqlStorage(props.getProperty("db.url"),
                    props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public static Config get() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public String getDbDriver() {
        return dbDriver;
    }
}