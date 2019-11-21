package com.sanya;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class TextFile {
    private String filePath = "C:\\Users\\Александр\\Desktop\\Учеба\\ООП\\Sem1\\LAB_№4\\T4\\Raw data file.txt";

    //Конструктор
    TextFile (String filePath) {
        try {
            if (!new File(filePath).createNewFile())
                System.out.println("File already exists!");
            else
                this.filePath = filePath;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //Заполняет новый объект класса Test данными из файла
    Test setTestInfo() throws FileNotFoundException {
        Scanner fileIn = new Scanner(new FileReader(filePath));
        int pts = fileIn.nextInt();
        return new Test(fileIn.nextInt(), fileIn.nextInt(), fileIn.nextInt(), pts, fileIn.nextLine().stripLeading());
    }
}
