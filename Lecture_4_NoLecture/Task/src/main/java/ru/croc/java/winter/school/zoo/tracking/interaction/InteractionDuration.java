package ru.croc.java.winter.school.zoo.tracking.interaction;

import java.time.LocalDateTime;

public class InteractionDuration {
    private int years = 0;
    private int months = 0;
    private int days = 0;
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;

    private LocalDateTime startTime;
    private LocalDateTime finishTime;


    public InteractionDuration(LocalDateTime startTime, LocalDateTime finishTime) {
        this.startTime = startTime;
        this.finishTime = finishTime;
    }


    public void computeInteractionDuration() {
        years = finishTime.getYear() - startTime.getYear();
        months = finishTime.getMonthValue() - startTime.getMonthValue();
        days = finishTime.getDayOfMonth() - startTime.getDayOfMonth();
        hours = finishTime.getHour() - startTime.getHour();
        minutes = finishTime.getMinute() - startTime.getMinute();
        seconds = finishTime.getSecond() - startTime.getSecond();
    }


    public int getYears() {
        return years;
    }

    public int getMonths() {
        return months;
    }

    public int getDays() {
        return days;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public String toString() {
        return (years + " years, "
                + months + " months, "
                + days + " days, "
                + hours + " hours, "
                + minutes + " minutes, "
                + seconds + " seconds");
    }
}
