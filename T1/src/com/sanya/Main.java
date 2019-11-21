package com.sanya;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Ex1();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void Ex1() throws IOException {

        //Создание файла в корневом каталоге
        File MyFile1 = new File("MyFile1.bin");
        MyFile1.createNewFile();
        if (MyFile1.exists())
            System.out.println("Файл " + MyFile1.getName() + " создан успешно");
        else
            System.out.println("Ошибка, файл не создан!");

        //Создание файла в папке по абсолютному пути
        String sep = File.separator;
        String absolutePath = "C:" + sep + "Users" + sep + "Александр" + sep + "Desktop" + sep + "Учеба" + sep +
                "ООП" + sep + "Sem1" + sep + "LAB_№4";
        File MyFile2 = new File(absolutePath, "MyFile2.bin");
        MyFile2.createNewFile();
        if (MyFile2.exists())
            System.out.println("Файл " + MyFile2.getName() + " создан успешно");
        else
            System.out.println("Ошибка, файл не создан!");

        //Создание файла в папке без абсолютного пути
        File MyFile3 = new File("C:\\JavaProgs", "MyFile3.bin");
        MyFile3.createNewFile();
        if (MyFile3.exists())
            System.out.println("Файл " + MyFile3.getName() + " создан успешно");
        else
            System.out.println("Ошибка, файл не создан!");

        //Создание папки
        File MyDir1 = new File(absolutePath, "TestDir");
        MyDir1.mkdir();
        if (MyDir1.exists() && MyDir1.isDirectory())
            System.out.println("Папка " + MyDir1.getName() + " создана успешно");
        else
            System.out.println("Ошибка, папка не создана!");

        System.out.println();
        Ex2(MyFile1, MyFile2, MyFile3, MyDir1);
        Ex3(MyFile1, MyFile2, MyFile3, MyDir1);
    }

    private static void Ex2 (File f1, File f2, File f3, File d1) {
        //Проверка типа - файл или папка
        if (f3.isFile())
            System.out.println(f3.getName() + " - файл");

        if (d1.isDirectory())
            System.out.println(d1.getName() + " - папка");

        //Проверка первого файла
        if (f1.exists())
            System.out.println("Файл " + f1.getName() + " находится в папке приожения");
        else
            System.out.println("Ошибка, файла в папке приложения нет!");

        //Вывод полного пути
        System.out.println("Полный путь к файлу " + f3.getName() + ": " + f3.getAbsolutePath());

        //Вывод размера файла
        System.out.println("Файл " + f2.getName() + " занимает " + f2.length() + " байтов\n");
    }

    private static void Ex3 (File f1, File f2, File f3, File d1) {
        String sep = File.separator;
        String absoluteAppPath = "C:" + sep + "Users" + sep + "Александр" + sep + "Desktop" + sep + "Учеба" + sep +
                "ООП" + sep + "Sem1" + sep + "LAB_№4" + sep + "T1";

        //Создание еще одной папки
        File MyDir2 = new File(absoluteAppPath, "MyDir2");
        MyDir2.mkdir();

        //Вывод имен файлов
        System.out.println("Список файлов в папке приложения " + Arrays.toString(MyDir2.getParentFile().list()));

        //Вывод файлов с путями и подсчет папок
        File[] files = MyDir2.getParentFile().listFiles();
        assert files != null;
        int dirCounter = 0;
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(file.getAbsolutePath() + " - папка");
                dirCounter++;
            } else
                System.out.println(file.getAbsolutePath() + " - файл");
        }
        System.out.println("В папке приложения " + dirCounter + " папок");

        //Удаление
        System.out.println("\nНажмите ENTER, чтобы удалить все созданные папки и файлы");
        Scanner wait = new Scanner(System.in);
        wait.nextLine();
        f1.delete();
        f2.delete();
        f3.delete();
        d1.delete();
        MyDir2.delete();
    }
}
