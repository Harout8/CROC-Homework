package ru.croc.java.winter.school.zoo.eventsrecords;

import java.time.LocalDateTime;


public class ContactRecord {
    /** Имя сотрудника */
    private String employee;

    /** Животное */
    private String animal;

    /** Местоположение по X сотрудника */
    private double x_employee;

    /** Местоположение по Y сотрудника */
    private double y_employee;

    /** Местоположение по X животного */
    private double x_animal;

    /** Местоположение по Y животного */
    private double y_animal;

    /** Время начала контакта. */
    private LocalDateTime time_start;

    /** Время окончания контакта. */
    private LocalDateTime time_end;

    /** Длительность контакта */
    private String contactDuration;

    long nanoseconds;


    /**
     * Запись о контакте сотрудника и животного.
     *
     * @param employee имя сотрудника
     * @param animal животное
     * @param x_employee координата X сотрудника
     * @param y_employee координата Y сотрудника
     * @param x_animal координата X животного
     * @param y_animal координата Y животного
     * @param time_start время начала контакта
     * @param time_end время окончания контакта
     */
    public ContactRecord(String employee,
                         String animal,
                         double x_employee,
                         double y_employee,
                         double x_animal,
                         double y_animal,
                         LocalDateTime time_start,
                         LocalDateTime time_end) {
        this.employee = employee;
        this.animal = animal;
        this.x_employee = x_employee;
        this.y_employee = y_employee;
        this.x_animal = x_animal;
        this.y_animal = y_animal;
        this.time_start = time_start;
        this.time_end = time_end;

        //nanoseconds = time_end.getNano() - time_start.getNano();

        // 1000000 наносекунд = 1 миллисекунда
        //int milliseconds = (int) (nanoseconds / (1000000));

        // 1000 миллисекунд = 1 секунда
        //int seconds = (int) (milliseconds / (1000));

        // 60 000 миллисекунд = 60 секунд = 1 минута
        //int minutes = (int) (milliseconds / (60 * 1000));

        // 3 600 секунд = 60 минут = 1 час
        //int hours = (int) (milliseconds / (60 * 60 * 1000));

        // 24 часа = 1 440 минут = 1 день
        //int days = (int) (milliseconds / (24 * 60 * 60 * 1000));

        this.contactDuration = ((Integer) (time_end.getYear() - time_start.getYear())).toString() + " years " +
                ((Integer) (time_end.getMonthValue() - time_start.getMonthValue())).toString() + " months " +
                ((Integer) (time_end.getDayOfMonth() - time_start.getDayOfMonth())).toString() + " days " +
                ((Integer) (time_end.getHour() - time_start.getHour())).toString() + " hours " +
                ((Integer) (time_end.getMinute() - time_start.getMinute())).toString() + " minutes " +
                ((Integer) (time_end.getSecond() - time_start.getSecond())).toString() + " seconds ";
                /*
                LocalDateTime.of(time_end.getYear() - time_start.getYear(),
                time_start.getMonthValue(),
                time_end.getDayOfMonth() - time_start.getDayOfMonth(),
                time_end.getHour() - time_start.getHour(),
                time_end.getMinute() - time_start.getMinute(),
                time_end.getSecond() - time_start.getSecond());
                */
    }


    public String getEmployee() {
        return employee;
    }

    public String getAnimal() {
        return animal;
    }

    public double getX_employee() {
        return x_employee;
    }

    public double getY_employee() {
        return y_employee;
    }

    public double getX_animal() {
        return x_animal;
    }

    public double getY_animal() {
        return y_animal;
    }

    public LocalDateTime getTime_start() {
        return time_start;
    }

    public LocalDateTime getTime_end() {
        return time_end;
    }

    public String getContactDuration() {
        return contactDuration;
    }

    public String toString() {
        String contactRecordInfo = employee + "\n" +
                animal + "\n" +
                x_employee + "\n" +
                y_employee + "\n" +
                x_animal + "\n" +
                y_employee + "\n" +
                time_start.toString() + "\n" +
                time_end.toString() + "\n" +
                contactDuration;

        return contactRecordInfo;
    }
}
