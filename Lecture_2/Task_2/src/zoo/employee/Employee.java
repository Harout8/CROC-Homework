package zoo.employee;

import zoo.animal.Animal;
import zoo.Zoo;

import java.util.Arrays;

public class Employee {
    private String name;
    private Zoo zoo;
    private Animal[] wards; // Подопечные животные
    private boolean hired = false;


    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Zoo zoo) {
        this.name = name;

        this.zoo = zoo;
        this.zoo.hireEmployee(this);
        this.hired = true;
    }

    public Employee(String name, Zoo zoo, Animal[] wards) {
        this.name = name;
        this.wards = wards;

        for (Animal animal : wards) {
            animal.setCaretaker(this);
        }

        this.zoo = zoo;
        this.zoo.hireEmployee(this);
        this.hired = true;
    }


    // Getters & Setters:

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal[] getWards() {
        return wards;
    }

    public void setWards(Animal[] wards) {
        if (hired) {
            this.wards = wards;
        } else {
            System.out.println("Нельзя назначить подопечного, т. к. этот человек не является сотрудником зоопарка !");
        }
    }

    public boolean isHired() {
        return hired;
    }

    public void setHired(boolean hired) {
        this.hired = hired;
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public void addWard(Animal animal) {
        if (hired) {
            if (wards == null) {
                wards = new Animal[0];
            }

            wards = Arrays.copyOf(wards, wards.length + 1);
            wards[wards.length - 1] = animal;

            animal.setCaretaker(this);
        } else {
            System.out.println("Нельзя назначить подопечного, т. к. " + name + " не является сотрудником зоопарка !");
        }
    }

    public void deleteWard(Animal animal) {
        Animal[] newWards = new Animal[wards.length - 1];

        for (int i = 0, j = 0; i < wards.length; i++) {
            if (wards[i] != animal) {
                newWards[j++] = wards[i];
            }
        }

        wards = newWards;

        animal.setCaretaker(null);
        animal.setHasCaretaker(false);
    }
}
