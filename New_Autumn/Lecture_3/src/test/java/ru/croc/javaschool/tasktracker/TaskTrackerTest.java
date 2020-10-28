package ru.croc.javaschool.tasktracker;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;


public class TaskTrackerTest {

    private String separator = System.getProperty("file.separator");
    private Executor executor = new Executor("1", "1", "1", "1");
    private Task task = new Task(1, "1", "1", executor, "1");

    @Test
    public void testCreateTask() {
        String path = System.getProperty("user.dir")
                + separator
                + "Tasks"
                + separator
                + task.getCode()
                + "_test"
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


        Task taskActual = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            taskActual = (Task) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(task.toString(), taskActual.toString());
    }

    @Test
    public void testChangeTask() {
        int code = 1;

        String path = System.getProperty("user.dir")
                + separator
                + "Tasks"
                + separator
                + code
                + "_test"
                + ".txt";

        Task taskActual = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            taskActual = (Task) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int commandNumber = 1;
        //int commandNumber = 2;

        switch (commandNumber) {
            case 1:
                taskActual.setStatus("11");
                break;
            case 2:
                Executor newValue = new Executor("11", "11", "11", "11");
                taskActual.setExecutor(newValue);
                break;
        }

        // Для commandNumber = 2
        //Executor executor = new Executor("11", "11", "11", "11");
        //Task taskExpected = new Task(1, "1", "1", executor, "1");

        // Для commandNumber = 1
        Task taskExpected = new Task(1, "1", "1", executor, "11");

        Assertions.assertEquals(taskExpected.toString(), taskActual.toString());
    }
}
