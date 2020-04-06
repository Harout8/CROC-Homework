package ru.croc.java.winter.school.zoo.tracking.workshift;


import ru.croc.java.winter.school.zoo.tracking.Tracked;

import java.time.LocalDateTime;


/**
 * Рабочая смена сотрудника.
 */
public class WorkShift {
    private final Tracked employee;
    private final LocalDateTime startTime;
    private LocalDateTime finishTime;

    /**
     * Рабочая смена отслеживаемого сотрудника.
     *
     * @param employee сотрудник
     * @param startTime время начала смены
     */
    public WorkShift(Tracked employee, LocalDateTime startTime) {
        this.employee = employee;
        this.startTime = startTime;
        finishTime = null;
    }


    /**
     * Длительноть смены сотрудника
     *
     * @return длительность смены сотрудника
     */
    public String shiftDuration() {
        return (finishTime.getDayOfMonth() - startTime.getDayOfMonth()) + " days, " +
                (finishTime.getHour() - startTime.getHour()) + " hours, " +
                (finishTime.getMinute() - startTime.getMinute()) + " minutes, " +
                (finishTime.getSecond() - startTime.getSecond()) + " seconds.";
    }


    /**
     * Время окончания смены.
     *
     * @return время, null если сотрудник еще работает
     */
    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    /**
     * Устанавливаем время окончания смены.
     *
     * @param finishTime время окончания
     */
    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public Tracked getEmployee() {
        return employee;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
