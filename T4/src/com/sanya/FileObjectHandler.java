package com.sanya;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class FileObjectHandler {
    private String filePath = "C:\\Users\\Александр\\Desktop\\Учеба\\ООП\\Sem1\\LAB_№4\\T4\\Serialized object.txt";
    private String fileCollectionPath = "C:\\Users\\Александр\\Desktop\\Учеба\\ООП\\Sem1\\LAB_№4\\T4\\Serialized collection.txt";
    private ArrayList<Test> testsList = new ArrayList<>();

    FileObjectHandler (String filePath) {
        try {
            if (!new File(filePath).createNewFile())
                System.out.println("File already exists!");
            else
                this.filePath = filePath;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    void outputTest() throws IOException {
        //Создание и заполнение объекта класса Test
        Test output = new Test();
        output.setLevel();
        output.setTestNumber();
        output.setNumberOfQuestions();
        output.setMaximumPoints();
        output.setTheme();

        //Запись в файл
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
        out.writeInt(output.getLevel());
        out.writeInt(output.getTestNumber());
        out.writeInt(output.getNumberOfQuestions());
        out.writeInt(output.getMaximumPoints());
        out.writeUTF(output.getTheme());

        //Закрытие потока
        out.close();
    }

    void inputTest () throws IOException {
        //Создание потока для считывания
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));

        //Считывание из файла в новый объект класса Test
        int pts = in.readInt();
        Test input = new Test(in.readInt(), in.readInt(), in.readInt(), pts, in.readUTF());

        //Проверка
        System.out.println("Info about a test: "+input.getLevel()+" "+input.getTestNumber()+" "+input.getNumberOfQuestions()
                +" "+input.getMaximumPoints()+" "+input.getTheme());
    }

    void addToCollection() {
        Scanner in = new Scanner(System.in);
        System.out.println("How many tests you want to add? (Positive integer number)");
        int num = in.nextInt();
        assert num > 0;

        //Создание объектов и добавление их в коллекцию
        for (int i = 0; i < num; i++) {
            Test T = new Test();
            T.setLevel();
            T.setTestNumber();
            T.setNumberOfQuestions();
            T.setMaximumPoints();
            T.setTheme();
            testsList.add(T);
        }
    }

    void outputCollection() throws IOException {
        //Создание потока для записи
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileCollectionPath));

        //Запись в файл
        for (Test T : testsList) {
            out.writeInt(T.getLevel());
            out.writeInt(T.getTestNumber());
            out.writeInt(T.getNumberOfQuestions());
            out.writeInt(T.getMaximumPoints());
            out.writeUTF(T.getTheme());
        }

        //Закрытие потока
        out.close();
    }

    void inputCollection() throws IOException {
        //Созданеи потока для чтения
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileCollectionPath));
        int pts;

        //Чтение и добавление в коллекцию
        while(true) {
            try {
                pts = in.readInt();
                testsList.add(new Test(in.readInt(), in.readInt(), in.readInt(), pts, in.readUTF()));
            } catch (EOFException e) {
                in.close();
                break;
            }
        }
    }

    void printCollection() {
        for (Test T : testsList) {
            System.out.println(T.getLevel()+" "+T.getTestNumber()+" "+T.getNumberOfQuestions()+" "+
                    T.getMaximumPoints()+" "+T.getTheme());
        }
    }

    FileObjectHandler getObject() {
        return this;
    }

    ArrayList<Test> getCollection() {
        return testsList;
    }
}
