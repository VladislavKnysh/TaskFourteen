package com.company;

import com.company.exception.FileWasFoundException;

import java.io.*;
import java.util.Objects;

public class FileFinder {
    private String fileName;
    private File directory;
    private File fileToFind;

    public FileFinder(File directory, String fileName) {
        this.fileName = fileName;
        this.directory = directory;
    }

    public File find() {
        try {
        //Step One
        if (checkDirectory(directory)) {
            findInDirectory(directory);
            return fileToFind;
        }
        //Step Two
        if (!checkDirectoryForMoreDirectories(directory)) {
            return null;
        } else {
            File[] directories = findSubDirectories(directory);
            for (File file : directories) {
                if (Objects.nonNull(operateFile(file))) {
                    return file;
                }

            }
        }
        return null;
    }catch (FileWasFoundException fileWasFoundException){return fileToFind;}
    }

    //Step Three
    private File operateFile(File file) throws FileWasFoundException {
        try {
            if (checkDirectory(file)) {
                findInDirectory(file);
                return fileToFind;
            }
            if (!checkDirectoryForMoreDirectories(file)) {
                return null;
            } else {
                File[] directories = findSubDirectories(file);
                for (File subFile : directories) {
                    if (Objects.nonNull(operateFile(subFile))) {
                        return subFile;
                    }
                }
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }

    }


    private boolean checkDirectory(File file) {

        File[] files = file.listFiles((dir, name) -> name.equalsIgnoreCase(fileName));

        if (files.length > 0) {
            return true;
        } else return false;

    }

    private void findInDirectory(File file) throws FileWasFoundException {
        File[] files = file.listFiles((dir, name) -> name.equalsIgnoreCase(fileName));

        if (files.length > 0) {
            fileToFind = files[0]
            ;
            throw new FileWasFoundException();
        }
    }


    private boolean checkDirectoryForMoreDirectories(File file) {

        File[] files = file.listFiles((dir, name) -> !dir.isFile());

        if (files.length > 0) {
            return true;
        } else return false;

    }

    private File[] findSubDirectories(File file) {
        File[] files = file.listFiles((dir, name) -> dir.isDirectory());
        return files;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }


}
