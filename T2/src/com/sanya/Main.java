package com.sanya;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            File newFile = FileManager.createFile();
            FileManager.fileOutput(newFile);
            FileManager.fileInput(newFile);
            File newNewFile = FileManager.makeNewTestsFile(newFile);
            FileManager.changeTestsNumberOfQuestions(newNewFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
