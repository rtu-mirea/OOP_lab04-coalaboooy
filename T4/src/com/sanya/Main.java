package com.sanya;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("##################################################################################\n"+
                    "First task:");
            TextFile first = new TextFile("C:\\Users\\Александр\\Desktop\\Учеба\\ООП\\Sem1\\LAB_№4\\T4\\Raw data file.txt");
            Test one = first.setTestInfo();
            System.out.println("Info about test: "+one.getLevel()+" "+one.getTestNumber()+" "+one.getNumberOfQuestions()
                    +" "+one.getMaximumPoints()+" "+one.getTheme());
            System.out.println("##################################################################################\n"+
                    "Second task:");
            FileObjectHandler second = new FileObjectHandler("C:\\Users\\Александр\\Desktop\\Учеба\\ООП\\Sem1\\LAB_№4\\T4\\Serialized object.txt");
            second.outputTest();
            second.inputTest();
            System.out.println("##################################################################################\n"+
                    "Third task:");
            FileObjectHandler third = new FileObjectHandler("C:\\Users\\Александр\\Desktop\\Учеба\\ООП\\Sem1\\LAB_№4\\T4\\Serialized collection.txt");
            third.addToCollection();
            System.out.println("Collection looks like this after addition of objects:");
            third.printCollection();
            third.outputCollection();
            third.inputCollection();
            System.out.println("Collection looks like this after write/read:");
            third.printCollection();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
