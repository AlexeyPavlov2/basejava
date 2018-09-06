package com.basejava.webapp;

import java.io.File;
import java.util.Objects;

public class MainScanFolders {
    private static int size = 0;
    private static int foldersCount  = 0;
    private static int filesCount = 0;

    public static void main(String[] args) {
        scanFolder(new File("."));
        System.out.println(String.format("Folers: %d, files: %d, total size: %dKB",
                foldersCount, filesCount, size));
    }

    public static void scanFolder(File dir) {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                foldersCount++;
                scanFolder(file);
            } else {
                System.out.println(file.getName());
                size += file.length();
                filesCount++;
            }

        }
    }
}

