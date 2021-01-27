package com.company;

import com.company.exception.FileWasFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
        return find(directory);
    }

    private File find(File directory) {
        if (directory.isDirectory()) {
            File[] directoryFiles = directory.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        find(file);
                    } else {
                        if (file.getName().equalsIgnoreCase(fileName)) {
                            fileToFind= file;
                        }
                    }
                }
            }
        }
    return fileToFind;}


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
