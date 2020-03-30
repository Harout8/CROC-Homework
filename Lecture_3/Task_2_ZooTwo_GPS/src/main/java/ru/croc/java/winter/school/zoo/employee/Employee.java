package ru.croc.java.winter.school.zoo.employee;

import ru.croc.java.winter.school.zoo.animal.Animal;
import ru.croc.java.winter.school.zoo.eventsrecords.ContactRecord;
import ru.croc.java.winter.school.zoo.eventsrecords.EmployeeComeAndLeaveRecord;
import ru.croc.java.winter.school.zoo.interfaces.Trackable;
import ru.croc.java.winter.school.zoo.eventsrecords.MovementRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Сотрудник.
 */
public class Employee implements Trackable {
    /** Имя. */
    private String name;

    /** Дата рождения. */
    private LocalDate dateOfBirth;

    /** Подопечные животные. */
    private Set<Animal> animals;

    /** Идентификатор датчика работника */
    private int sensorID;

    /** Координата по оси X */
    private double x;

    /** Координата по оси Y */
    private double y;

    /** Журнал перемещений */
    private List<MovementRecord> movementRecords;

    /** Журнал прихода на работу и ухода с неё */
    private List<EmployeeComeAndLeaveRecord> employeeComeAndLeaveRecords;


    /**
     * Сотрудник.
     *
     * @param name ФИО
     * @param dateOfBirth дата рождения
     */
    public Employee(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        animals = new HashSet<>();
        movementRecords = new ArrayList<>();
        employeeComeAndLeaveRecords = new ArrayList<>();
    }

    /**
     * Поручить сотруднику животное.
     *
     * @param animal животное
     */
    public void add(Animal animal) {
        animals.add(animal);
    }

    /**
     * Снимаем ответственность за животное.
     *
     * @param animal животное
     */
    public void remove(Animal animal) {
        animals.remove(animal);
    }

    /**
     * Находится ли животное на попичении?
     *
     * @param animal животное
     * @return true, если находится
     */
    public boolean isCare(Animal animal) {
        return animals.contains(animal);
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public List<MovementRecord> getMovementRecords() {
        return movementRecords;
    }

    public List<EmployeeComeAndLeaveRecord> getEmployeeComeAndLeaveRecords() {
        return employeeComeAndLeaveRecords;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", animals=" + animals +
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

        if ((this.x == 0) && (this.y == 0)) {
            if (movementRecords.size() > 1) {
                if ((movementRecords.get(movementRecords.size() - 2).getX() < 0)
                    | (movementRecords.get(movementRecords.size() - 2).getY() < 0)) {
                    employeeComeAndLeaveRecords.add(new EmployeeComeAndLeaveRecord(name,
                            LocalDateTime.of(2020, 3, 30, 21, 55, 55),
                            "Приход"));
                } else {
                    employeeComeAndLeaveRecords.add(new EmployeeComeAndLeaveRecord(name,
                            LocalDateTime.of(2020, 3, 30, 21, 55, 55),
                            "Уход"));
                }
            }
        }
    }

    /**
     * Предоставление информации о текущем местонахождении сотрудника
     * @return текущее местонахождение сотрудника
     */
    public String getLocation() {
        String locationInfo = "\n";
        locationInfo += "Координата X: " + this.x + "\n" + "Координата Y: " + this.y;

        return locationInfo;
    }
}
