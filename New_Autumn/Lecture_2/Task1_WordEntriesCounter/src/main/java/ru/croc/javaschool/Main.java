package ru.croc.javaschool;

public class Main {
    public static void main(String[] args) {
        String text = "We will we will rock you, We will we will rock you";

        TextParser textParser = new TextParser();
        textParser.countWordsEntries(text);
    }
}
