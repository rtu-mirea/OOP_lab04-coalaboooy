package com.sanya;

public class Test {
    private int testNumber;
    private int numberOfQuestions;
    private int maximumPoints;
    private int level;
    private String theme;

    //Конструктор
    Test(int testNumber, int numberOfQuestions, int maximumPoints, int level, String theme) {
        this.setLevel(level);
        this.setTestNumber(testNumber);
        this.setNumberOfQuestions(numberOfQuestions);
        this.setMaximumPoints(maximumPoints);
        this.setTheme(theme);
    }

    //Сеттеры
    private void setLevel(int level) {
        this.level = level;
    }

    private void setTestNumber(int testNumber) {
        this.testNumber = testNumber;
    }

    private void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    private void setMaximumPoints(int maximumPoints) {
        this.maximumPoints = maximumPoints;
    }

    private void setTheme(String theme) {
        this.theme = theme;
    }

    //Геттеры
    int getLevel() {
        return this.level;
    }

    int getTestNumber() {
        return this.testNumber;
    }

    int getNumberOfQuestions() {
        return this.numberOfQuestions;
    }

    int getMaximumPoints() {
        return this.maximumPoints;
    }

    String getTheme() {
        return this.theme;
    }

    //Дополнительные методы
    public String getTest(int testNumber) {
        return "Test level is " + this.getLevel() + " and maximum possible points to get are " + this.getMaximumPoints();
    }

    public boolean equalsByPoints(Test test) {
        return this.getMaximumPoints() == test.getMaximumPoints();
    }
}
