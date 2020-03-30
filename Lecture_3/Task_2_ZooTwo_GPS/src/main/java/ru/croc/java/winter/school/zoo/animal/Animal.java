package ru.croc.java.winter.school.zoo.animal;

import ru.croc.java.winter.school.zoo.eventsrecords.ContactRecord;
import ru.croc.java.winter.school.zoo.interfaces.Trackable;
import ru.croc.java.winter.school.zoo.eventsrecords.MovementRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Животное.
 */
public class Animal implements Trackable {
    /** Название. */
    private String name;

    /** Дата рождения. */
    private LocalDate dateBirth;

    /** Журнал болезней. */
    private List<IllnessRecord> illnessRecords;

    /** Идентификатор датчика животного */
    private int sensorID;

    /** Координата по оси X */
    private double x;

    /** Координата по оси Y */
    private double y;

    /** Журнал перемещений */
    private ArrayList<MovementRecord> movementRecords;


    /**
     * Животное.
     *
     * @param name название
     * @param dateBirth дата рождения
     */
    public Animal(String name, LocalDate dateBirth) {
        this.name = name;
        this.dateBirth = dateBirth;
        illnessRecords = new ArrayList<>();
        movementRecords = new ArrayList<>();
    }

    /**
     * Добавляем запись в журнал болезней.
     *
     * @param illnessRecord запись о болезни.
     */
    public void add(IllnessRecord illnessRecord) {
        illnessRecords.add(illnessRecord);
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public List<IllnessRecord> getIllnessRecords() {
        return illnessRecords;
    }

    public List<MovementRecord> getMovementRecords() {
        return movementRecords;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", dateBirth=" + dateBirth +
                ", illnessRecords=" + illnessRecords +
                '}';
    }

    @Override
    public int getSensorID() {
        return sensorID;
    }

    public void setSensorID(int sensorID) {
        this.sensorID = sensorID;
    }

    /**
     * Задать новые координаты, полученные со спутника
     * @param x координата по оси X
     * @param y координата по оси Y
     */
    @Override
    public void setNewCoordinates(double x, double y) {
        this.x = x;
        this.y = y;

        movementRecords.add(new MovementRecord(this.x, this.y, LocalDateTime.now()));
    }

    /**
     * Предоставление информации о текущем местонахождении животного
     * @return текущее местонахождение животного
     */
    public String getLocation() {
        String locationInfo = "\n";
        locationInfo += "Координата X: " + this.x + "\n" + "Координата Y: " + this.y;

        return locationInfo;
    }
}
