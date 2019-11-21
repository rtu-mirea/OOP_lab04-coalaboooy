package com.sanya;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        try {
            Ex1();
            Ex2();
            Ex3();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void Ex1() throws IOException {
        //Создание потоков для чтения и записи
        FileReader in = new FileReader("C:\\Users\\Александр\\Desktop\\Учеба\\ООП\\Sem1\\LAB_№4\\T3\\T1.txt");
        FileWriter out = new FileWriter("C:\\Users\\Александр\\Desktop\\Учеба\\ООП\\Sem1\\LAB_№4\\T3\\T2.txt");

        //Посимвольное считывание и запись файла
        int chrs = in.read();
        while (chrs != -1) {
            out.write(chrs);
            chrs = in.read();
        }

        //Закрытие потоков
        in.close();
        out.close();
    }

    private static void Ex2() throws IOException {
        //Создание потоков и буфера для чтения и записи файла
        BufferedReader inb = new BufferedReader
                (new FileReader("C:\\Users\\Александр\\Desktop\\Учеба\\ООП\\Sem1\\LAB_№4\\T3\\A.txt"),128);
        BufferedWriter outb = new BufferedWriter
                (new FileWriter("C:\\Users\\Александр\\Desktop\\Учеба\\ООП\\Sem1\\LAB_№4\\T3\\B.txt"),128);
        char[] buf = new char[128];

        //Буферизированное чтение и запись файла по 128 символов
        for (int i = 0; i < 4; i++) {
            inb.read(buf);
            outb.write(buf);
            if (i != 3) outb.write("\n");
        }

        //Закрытие потоков
        inb.close();
        outb.close();
    }

    private static void Ex3 () throws IOException {
        System.out.println("Стандартная кодировка системы - "+Charset.defaultCharset().name());

        //Создание потока с кодировкой Ср1251 для считывания из файла
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream
                ("C:\\Users\\Александр\\Desktop\\Учеба\\ООП\\Sem1\\LAB_№4\\T3\\src\\R.txt"),"Cp1251"));

        //Считывание файла
        System.out.println(in.readLine()+"\nОшибка, символы в файле используют другую кодировку, повторим считывание с правильной кодировкой:");

        //Закрытие потока
        in.close();

        //Пересоздание потока с кодировкой UTF8
        in = new BufferedReader(new InputStreamReader(new FileInputStream
                ("C:\\Users\\Александр\\Desktop\\Учеба\\ООП\\Sem1\\LAB_№4\\T3\\src\\R.txt"), StandardCharsets.UTF_8));

        //Считывание файла c правильной кодировкой
        System.out.println(in.readLine()+"\nТеперь считанные символы выводятся правильно");

        //Закрытие потока
        in.close();
    }
}
