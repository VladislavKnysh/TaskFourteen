package com.company;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        //      FileFinder fileFinder = new FileFinder(new File("src/findMeThere"), "heyImHere");
       FileFinder fileFinder = new FileFinder(new File("src/"), "heyImHere");

        try {
            System.out.println("File was found at: " + fileFinder.find().getAbsolutePath());
        } catch (NullPointerException e) {
            System.out.println("There is no such file as " + fileFinder.getFileName() + " at " + fileFinder.getDirectory().getAbsolutePath());
        }
    }
}
