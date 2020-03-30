package ru.croc.java.winter.school.zoo.eventsrecords;


import java.time.LocalDateTime;


public class EmployeeComeAndLeaveRecord {
    /** Имя сотрудника */
    private String employee;

    /** Время прихода на работу. */
    private LocalDateTime time;

    /** Уход или приход с работы/на работу. */
    private String action;


    /**
     * Запись о приходе и уходе сотрудника на работу/с работы
     *
     * @param employee имя сотрудника
     * @param time время
     * @param action отметка об уходе/приходе с работы/на работу
     */
    public EmployeeComeAndLeaveRecord(String employee, LocalDateTime time, String action) {
        this.employee = employee;
        this.time = time;
        this.action = action;
    }


    public String getEmployee() {
        return employee;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }


    public String toString() {
        return employee + ", " + action + ", " + time + "\n";
    }
}
