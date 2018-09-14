package com.basejava.webapp;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class MainScanFolders {
    private static int size = 0;
    private static int foldersCount = 0;
    private static int filesCount = 0;
    private static int counter = 0;

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Scanning directory " + new File(".")
                .getCanonicalPath().toUpperCase() + ":");
        scanFolder(new File("."));
        System.out.println(String.format("Directories: %d, files: %d, total size: %dKB",
                foldersCount, filesCount, size));
    }

    private static void scanFolder(File dir) throws IOException, ParseException {
        for (File file : dir.listFiles()) {
            print(file);
            if (file.isDirectory()) {
                counter++;
                scanFolder(file);
            }
        }
        counter--;
    }

    private static void print(File file) throws IOException, ParseException {
        for (int i = 0; i < counter; i++)
            System.out.print(" ");
        if (file.isDirectory()) {
            foldersCount++;
            System.out.println(file.getCanonicalPath().toUpperCase());
        } else {
            filesCount++;
            size += file.length();
            System.out.println(file.getName());
        }
    }
}

