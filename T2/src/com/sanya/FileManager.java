package com.sanya;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

class FileManager {

    private static LinkedList<Test> sameLevelTests = new LinkedList<>();

    static File createFile() throws IOException {
        Scanner in = new Scanner(System.in);

        //Создание файла для записи
        System.out.println("Enter file name:");
        File output = new File(String.format("%s.bin", in.nextLine()));
        output.createNewFile();
        System.out.println(String.format("\"File named %s exists\" is %b", output.getName(), output.exists()));
        return output;
    }

    static void fileOutput(File output) throws IOException {
        Scanner in = new Scanner(System.in);

        //Создание потока для записи даных
        DataOutputStream out = new DataOutputStream(new FileOutputStream(output));

        System.out.println("Enter test's themes, splitting them by '; '");
        String[] themes = in.nextLine().split("; ");

        for (String theme : themes) {
            //Создание объекта теста и ввод данных
            System.out.println("Enter " + theme + " test's info as following:\nTest's level\nTest's number" +
                    "\nTest's number of questions\nTest's maximum possible points");
            Test test = new Test(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), theme);

            //Запись данных в файл
            out.writeInt(test.getTestNumber());
            out.writeInt(test.getNumberOfQuestions());
            out.writeInt(test.getMaximumPoints());
            out.writeInt(test.getLevel());
            out.writeUTF(test.getTheme());
            out.flush();
        }
        out.close();
    }

    static void fileInput(File input) throws IOException {
        //Создание потока для считывания и списка для хранения всех тестов
        DataInputStream inp = new DataInputStream(new FileInputStream(input));
        LinkedList<Test> tests = new LinkedList<>();
        int maxPoints;

        //Считывание информации о тестах и создание их в списке
        while (true) {
            try {
                maxPoints = inp.readInt();
                Test T = new Test(inp.readInt(), inp.readInt(), inp.readInt(), maxPoints, inp.readUTF());
                tests.add(T);
            } catch (EOFException e) {
                inp.close();
                break;
            }
        }

        //Выбор тех тестов, у которых уровень совпадает с уровнем первого теста, и занесение их в новый список
        int level = tests.get(0).getLevel();
        for (Test test : tests) {
            if (test.getLevel() == level)
                sameLevelTests.add(test);
        }
    }

    static File makeNewTestsFile(File source) throws IOException {
        //Создание нового файла для хранения нового, отсортированного списка
        File destination = new File(source.getName().replace(".bin", "")+"_Sorted.bin");
        destination.createNewFile();

        //Создание потоков для чтения и записи
        RandomAccessFile rStream = new RandomAccessFile(source, "r");
        RandomAccessFile wStream = new RandomAccessFile(destination, "rw");
        int lvl, num, qsts, pts;
        String theme;

        while (true) {
            try {
                //Считывание информации о тесте из файла
                lvl = rStream.readInt();
                num = rStream.readInt();
                qsts = rStream.readInt();
                pts = rStream.readInt();
                theme = rStream.readUTF();

                //Если такой тест находится в списке отсортированных тестов, записываем его в файл
                for (Test original : sameLevelTests) {
                    if (original.getLevel() == lvl && original.getTestNumber() == num &&
                            original.getNumberOfQuestions() == qsts && original.getMaximumPoints() == pts &&
                            original.getTheme().equals(theme)) {
                        wStream.writeInt(lvl);
                        wStream.writeInt(num);
                        wStream.writeInt(qsts);
                        wStream.writeInt(pts);
                        wStream.writeUTF(String.format("%-20s", theme)); //Форматирование сроки пробелами справа так,
                                                                         //чтобы она занимала 20 символов
                    }
                }
            } catch (EOFException e) {
                rStream.close();
                wStream.close();
                break;
            }
        }

        //Тест, чтобы проверить, что wStream произвел запись верно
        /*DataInputStream dis = new DataInputStream(new FileInputStream(destination));
        int lvl1, num1, qsts1, pts1;
        String theme1;
        while (true) {
            try {
                lvl1 = dis.readInt();
                num1 = dis.readInt();
                qsts1 = dis.readInt();
                pts1 = dis.readInt();
                theme1 = dis.readUTF();
                System.out.println(lvl1 + " " + num1 + " " + qsts1 + " " + pts1 + " " + theme1);
            } catch (EOFException e) {
                dis.close();
                break;
            }
        }*/

        //Возвращаем созданный файл для дальнейшего использования
        return destination;
    }

    static void changeTestsNumberOfQuestions(File subject) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter new number of questions in tests - an integer positive number:");
        int quantity = in.nextInt();

        //Обработка пользовательского ввода
        if (quantity < 0) {
            throw new IOException("A number of questions can't be less than zero!");
        }
        else {
            //Создание потока для чтения/записи в файл
            RandomAccessFile rwStream = new RandomAccessFile(subject, "rw");
            while (true) {
                try {
                    rwStream.readInt(); //С помощью чтения реализовано передвижение по файлу
                    rwStream.readInt();
                    rwStream.writeInt(quantity);
                    rwStream.readInt();
                    rwStream.readUTF();
                } catch (EOFException e) {
                    rwStream.close();
                    break;
                }
            }
        }

        //Тест для проверки перезаписи кол-ва вопросов
        /*DataInputStream dis = new DataInputStream(new FileInputStream(subject));
        int lvl1, num1, qsts1, pts1;
        String theme1;
        while (true) {
            try {
                lvl1 = dis.readInt();
                num1 = dis.readInt();
                qsts1 = dis.readInt();
                pts1 = dis.readInt();
                theme1 = dis.readUTF();
                System.out.println(lvl1 + " " + num1 + " " + qsts1 + " " + pts1 + " " + theme1);
            } catch (EOFException e) {
                dis.close();
                break;
            }
        }*/
    }
}
