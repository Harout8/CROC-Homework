package ru.croc.java.winter.school.zoo;

import ru.croc.java.winter.school.zoo.animal.Animal;
import ru.croc.java.winter.school.zoo.employee.Employee;
import ru.croc.java.winter.school.zoo.eventsrecords.ContactRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Зоопарк.
 */
public class Zoo {
    /** Название. */
    private String title;

    /** Животные. */
    private List<Animal> animals;

    /** Сотрудники. */
    private List<Employee> employees;

    /** Журнал контактов сотрудников с животными */
    private List<ContactRecord> contactRecords;

    double a = 0.0;

    /** Длина и ширина зоопарка */
    double b = 1000.0;

    /** Поле для генерации уникальных идентификаторов */
    int idGen = 0;


    /**
     * Зоопарк.
     *
     * @param title название
     */
    public Zoo(String title) {
        this.title = title;
        animals = new ArrayList<>();
        employees = new ArrayList<>();
        contactRecords = new ArrayList<>();
    }


    /**
     * Добавляем животное сотруднику.
     *
     * @param animal животное
     * @param employee опекун
     */
    public void add(Animal animal, Employee employee) {
        if (!animals.contains(animal) && employees.contains(employee)) {
            animals.add(animal);
            animal.setSensorID(++idGen);
            employee.add(animal);
        } else {
            // TODO что делать
        }
    }

    /**
     * Добавляем сотрудника.
     *
     * @param employee сотрудник
     */
    public void add(Employee employee) {
        employees.add(employee);
        employee.setSensorID(++idGen);
    }

    /**
     * Добавляем сотрудников.
     *
     * @param employees сотрудники
     */
    public void add(Employee... employees) {
        for (Employee employee : employees) {
            add(employee);
        }
    }

    /**
     * Удаляем животное.
     *
     * @param animal животное
     */
    public void remove(Animal animal) {
        for (Employee employee : employees) {
            if (employee.isCare(animal)) {
                employee.remove(animal);
            }
        }
        animals.remove(animal);
    }

    /**
     * Удаление сотрудника.
     *
     * @param employee сотрудник
     */
    public void remove(Employee employee) {
        if (employees.contains(employee) && employee.getAnimals().isEmpty()) {
            employees.remove(employee);
        } else {
            // TODO учим исключения
        }
    }


    public String getTitle() {
        return title;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<ContactRecord> getContactRecords() {
        return contactRecords;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "title='" + title + '\'' +
                ", animals=" + animals +
                ", employees=" + employees +
                '}';
    }


    public void updateLocations() {
        int i = 1;

        for (Employee employee : employees) {
            //employee.setNewCoordinates(a + Math.random() * b, a + Math.random() * b);
            employee.setNewCoordinates(100 + i, 100 + i);
            i++;
        }

        i = 1;
        for (Animal animal : animals) {
            //animal.setNewCoordinates(a + Math.random() * b, a + Math.random() * b);
            animal.setNewCoordinates(100 - i, 100 - i);
            i++;
        }

        for (Animal animal : animals) {
            for (Employee employee : employees) {
                if ((between(employee.getX(), animal.getX() - 3, animal.getX() + 3))
                        && (between(employee.getY(), animal.getY() -3, animal.getY() + 3))) {
                    contactRecords.add(new ContactRecord(employee.getName(),
                            animal.getName(),
                            employee.getX(),
                            employee.getY(),
                            animal.getX(),
                            animal.getY(),
                            LocalDateTime.of(2020, 3, 30, 20, 0, 0),
                            LocalDateTime.of(2020, 3, 30, 20, 15, 0)));
                }
            }
        }
    }


    private boolean between(double coordinate, double start, double end) {
        return (coordinate > start) & (coordinate < end);
    }
}
