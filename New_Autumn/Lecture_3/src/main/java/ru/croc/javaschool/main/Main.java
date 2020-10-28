package ru.croc.javaschool.main;

import ru.croc.javaschool.tasktracker.TaskTracker;

public class Main {
    public static void main(String[] args) {
        TaskTracker taskTracker = new TaskTracker();
        taskTracker.showMenu();
    }
}
