package ru.croc.javaschool.tasktracker;


import java.io.*;
import java.util.Scanner;


public class TaskTracker {

    private Scanner console = new Scanner(System.in);
    private int commandNumber;
    private String separator = System.getProperty("file.separator");

    public void showMenu() {
        System.out.println("Меню:\n");

        System.out.println(" 1. Создать задачу");
        System.out.println(" 2. Изменить задачу");

        getUserCommand();

        switch (commandNumber) {
            case 1:
                createTask();
                break;
            case 2:
                changeTask();
                break;
        }
    }

    private void getUserCommand() {
        System.out.print("\nВведите номер команды: ");
        commandNumber = console.nextInt();
        console.nextLine();
    }

    private void createTask() {
        System.out.println("Введите данные для новой задачи:");

        System.out.print("\n Код задачи: ");
        int code = console.nextInt();
        console.nextLine();

        System.out.print("\n Название задачи: ");
        String name = console.nextLine();

        System.out.print("\n Описание задачи: ");
        String description = console.nextLine();


        System.out.print("\n Исполнитель задачи: ");
        System.out.print("\n  Имя исполнителя: ");
        String firstName = console.nextLine();

        System.out.print("\n  Фамилия исполнителя: ");
        String lastName = console.nextLine();

        System.out.print("\n  Отчество исполнителя: ");
        String middleName = console.nextLine();

        System.out.print("\n  Должность исполнителя: ");
        String position = console.nextLine();

        Executor executor = new Executor(firstName, lastName, middleName, position);

        System.out.print("\n Статус задачи: ");
        String status = console.nextLine();

        Task task = new Task(code, name, description, executor, status);
        System.out.println(task.toString());


        String path = System.getProperty("user.dir")
                + separator
                + "Tasks"
                + separator
                + code
                + ".txt";
        File file = new File(path);


        try {
            // Создаём поток для сохранения в файл
            FileOutputStream outputStream = new FileOutputStream(file);

            // Создаём поток для сериализации объекта
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // Сохраняем задачу в файл
            objectOutputStream.writeObject(task);

            // Закрываем поток и освобождаем ресурсы
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeTask() {
        System.out.print("\nВведите код изменяемой задачи: ");
        int code = console.nextInt();

        String path = System.getProperty("user.dir")
                + separator
                + "Tasks"
                + separator
                + code
                + ".txt";

        Task task = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            task = (Task) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\nЧто вы хотите изменить: ");
        System.out.println(" 1 - статус ");
        System.out.println(" 2 - исполнителя ");

        getUserCommand();

        switch (commandNumber) {
            case 1:
                changeStatus(task);
                break;
            case 2:
                changeExecutor(task);
                break;
        }

        System.out.println(task.toString());
    }

    private void changeStatus(Task task) {
        System.out.print("\n Статус задачи: ");
        String newValue = console.nextLine();
        task.setStatus(newValue);
    }

    private void changeExecutor(Task task) {
        System.out.print("\n Исполнитель задачи: ");
        System.out.print("\n  Имя исполнителя: ");
        String firstName = console.nextLine();

        System.out.print("\n  Фамилия исполнителя: ");
        String lastName = console.nextLine();

        System.out.print("\n  Отчество исполнителя: ");
        String middleName = console.nextLine();

        System.out.print("\n  Должность исполнителя: ");
        String position = console.nextLine();

        Executor newValue = new Executor(firstName, lastName, middleName, position);
        task.setExecutor(newValue);
    }
}
