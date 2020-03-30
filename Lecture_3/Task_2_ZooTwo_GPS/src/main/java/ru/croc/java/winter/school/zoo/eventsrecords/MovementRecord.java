package ru.croc.java.winter.school.zoo.eventsrecords;

import java.time.LocalDateTime;


public class MovementRecord {
    /** Местоположение по X */
    private double x;

    /** Местоположение по Y */
    private double y;

    /** Время перемещения. */
    private LocalDateTime time;


    /**
     * Запись о перемещении.
     *
     * @param x координата по X
     * @param y координата по Y
     * @param time время
     */
    public MovementRecord(double x, double y, LocalDateTime time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
