package com.basejava.webapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainScanFolders {
    private static int size = 0;
    private static int foldersCount = 0;
    private static int filesCount = 0;
    private static int counter = 0;
    private static boolean verboseFlag = false;

    public static void main(String[] args) throws IOException, ParseException {
        if (args.length != 0 && args[0].equals("-v")) {
            verboseFlag = true;
        }

        System.out.println("Scanning directory " + new File( "." ).getCanonicalPath() + " ...");
        scanFolder(new File("."));
        System.out.println(String.format("Directories: %d, files: %d, total size: %d Kbyte",
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

    static void print(File file) throws IOException, ParseException {
        for (int i = 0; i < counter; i++)
            System.out.print(" ");

        BasicFileAttributes attr = Files.getFileAttributeView(Paths.get(file.getCanonicalPath()), BasicFileAttributeView.class).readAttributes();
        FileTime creationTime = attr.creationTime();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ");
        String cTime = df.format(creationTime.toMillis());
        String mTime = df.format(attr.lastModifiedTime().toMillis());

        if (file.isDirectory()) {
            foldersCount++;
            if (verboseFlag) {
                System.out.println("Dir: " + file.getCanonicalPath() + ", created: " + cTime + ", modified: " + mTime);
            } else {
                System.out.println("Dir: " + file.getCanonicalPath());
            }
        } else {
            filesCount++;
            size += file.length();
            if (verboseFlag) {
                System.out.println(file.getName() + ", created: " + cTime + ", modified: " + mTime + ", length: " + file.length() + " Kbyte");
            } else {
                System.out.println(file.getName());
            }
        }
    }
}
