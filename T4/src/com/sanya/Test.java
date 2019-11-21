package com.sanya;

import java.util.Scanner;

public class Test {
    private int testNumber;
    private int numberOfQuestions;
    private int maximumPoints;
    private int level;
    private String theme;
    private Scanner in = new Scanner(System.in);
    //Конструкторы
    Test () {}

    Test(int testNumber, int numberOfQuestions, int maximumPoints, int level, String theme) {
        this.testNumber = testNumber;
        this.numberOfQuestions = numberOfQuestions;
        this.maximumPoints = maximumPoints;
        this.level = level;
        this.theme = theme;
    }

    //Сеттеры с вводом из консоли
    void setTestNumber() {
        System.out.println("Enter test's number:");
        this.testNumber = in.nextInt();
    }

    void setNumberOfQuestions() {
        System.out.println("Enter test's number of questions:");
        this.numberOfQuestions = in.nextInt();
    }

    void setLevel() {
        System.out.println("Enter test's level:");
        this.level = in.nextInt();
    }

    void setMaximumPoints() {
        System.out.println("Enter test's maximum points:");
        this.maximumPoints = in.nextInt();
    }

    void setTheme() {
        System.out.println("Enter test's theme:");
        this.theme = in.next();
    }

    //Геттеры
    String getTheme() {
        return theme;
    }

    int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    int getLevel() {
        return level;
    }

    int getTestNumber() {
        return testNumber;
    }

    int getMaximumPoints() {
        return maximumPoints;
    }

    //Возвращает этот объект
    public Test getObject() {
        return this;
    }
}
