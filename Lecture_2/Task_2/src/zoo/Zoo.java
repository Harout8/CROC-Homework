package zoo;

import zoo.animal.Animal;
import zoo.employee.Employee;

import java.util.Arrays;

public class Zoo {
    private String title;
    private Animal[] animals;
    private Employee[] employees;


    public Zoo() {
        this("zoo.Zoo");
    }

    public Zoo(String title) {
        this.title = title;
    }

    public Zoo(String title, Animal[] animals, Employee[] employees) {
        this.title = title;
        this.animals = animals;
        this.employees = employees;
    }


    public String getTitle() {
        return title;
    }

    public Animal[] getAnimals() {
        return animals;
    }

    public Employee[] getEmployees() {
        return employees;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void add(Animal animal) {
        if (animals == null) {
            animals = new Animal[0];
        }

        animals = Arrays.copyOf(animals, animals.length + 1);
        animals[animals.length - 1] = animal;
    }

    public void add(Animal animal, boolean wasBorn) {
        if (animals == null) {
            animals = new Animal[0];
        }

        animals = Arrays.copyOf(animals, animals.length + 1);
        animals[animals.length - 1] = animal;

        animal.setWasBorn(wasBorn);
        animal.setWasBought(!wasBorn);
    }

    public void delete(Animal animal, boolean wasSold) {
        Animal[] newAnimals = new Animal[animals.length - 1];

        for (int i = 0, j = 0; i < animals.length; i++) {
            if (animals[i] != animal) {
                newAnimals[j++] = animals[i];
            }
        }

        animals = newAnimals;

        animal.setDead(!wasSold);
        animal.setWasSold(wasSold);
    }


    public void hireEmployee(Employee employee) {
        if (employees == null) {
            employees = new Employee[0];
        }

        employees = Arrays.copyOf(employees, employees.length + 1);
        employees[employees.length - 1] = employee;

        employee.setHired(true);
        employee.setZoo(this);
    }

    public void fireEmployee(Employee employee) {
        if (employee.getWards().length == 0) {
            Employee[] newEmployees = new Employee[employees.length - 1];

            for (int i = 0, j = 0; i < employees.length; i++) {
                if (employees[i] != employee) {
                    newEmployees[j++] = employees[i];
                }
            }

            employees = newEmployees;
        } else {
            System.out.println("Нельзя уволить " + employee.getName() + ", т. к. у этого сотрудника ещё есть подопечные животные !");
        }

        employee.setHired(false);
        employee.setZoo(null);
    }
}
