package com.basejava.webapp;

import java.io.File;
import java.io.IOException;

public class MainScanFolders {
    public static void main(String[] args) throws IOException {
        scanFolder(new File("."), "");
    }

    private static void scanFolder(File dir, String space) throws IOException {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                System.out.println(space + file.getName().toUpperCase());
                scanFolder(file, space + " ");
            } else {
                System.out.println(space + file.getName());
            }
        }
    }

}

