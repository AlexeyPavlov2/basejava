package com.basejava.webapp;

import com.basejava.webapp.storage.Storage;
import com.basejava.webapp.util.TestData;

public class MainPopulateDB {
    public static void main(String[] args) {
        Storage sqlStorage = Config.get().getStorage();
        sqlStorage.clear();
        TestData.fillTestData();

        sqlStorage.save(TestData.RESUME1);
        sqlStorage.save(TestData.RESUME2);
        sqlStorage.save(TestData.RESUME3);
        sqlStorage.save(TestData.RESUME4);
        sqlStorage.save(TestData.RESUME5);


    }
}
