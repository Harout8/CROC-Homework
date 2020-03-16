package zoo.animal;

import zoo.Zoo;
import zoo.employee.Employee;

import java.time.LocalDate;
import java.util.Arrays;

public class Animal {
    private String title;
    private Zoo zoo;

    private Employee caretaker;
    private boolean hasCaretaker;

    private double cost;

    private String[] diseases;
    private DiseaseDiary diseaseDiary;

    private boolean wasBorn;
    private boolean wasBought;
    private boolean isDead = false;
    private boolean wasSold = false;



    public Animal(String title) {
        this.title = title;
    }

    public Animal(String title, Zoo zoo) {
        this.title = title;
        this.zoo = zoo;
    }

    public Animal(String title, Zoo zoo, DiseaseDiary diseaseDiary) {
        this.title = title;
        this.zoo = zoo;
        this.diseaseDiary = diseaseDiary;
    }

    public Animal(String title, Zoo zoo, Employee caretaker, double cost, DiseaseDiary diseaseDiary, boolean wasBorn, boolean wasBought) {
        this.title = title;
        this.zoo = zoo;

        this.caretaker = caretaker;
        this.hasCaretaker = true;

        this.cost = cost;
        this.diseaseDiary = diseaseDiary;
        this.wasBorn = wasBorn;
        this.wasBought = wasBought;
    }


    // Getters:

    public String getTitle() {
        return title;
    }

    public Employee getCaretaker() {
        return caretaker;
    }

    public boolean hasCaretaker() {
        if (!hasCaretaker) {
            System.out.print("\n");
            System.out.println("У животного " + title + " нет сотрудника, ухажваещего за ним !");
            System.out.println("Пожалуйста, назначьте ему ответственого сотрудника.\n");
        }

        return hasCaretaker;
    }

    public double getCost() {
        return cost;
    }

    public String[] getDisease() {
        return diseases;
    }

    public DiseaseDiary getDiseaseDiary() {
        return diseaseDiary;
    }

    public boolean wasBorn() {
        return wasBorn;
    }

    public boolean wasBought() {
        return wasBought;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean wasSold() {
        return wasSold;
    }

    public Zoo getZoo() {
        return zoo;
    }


    // Setters:

    public void setCaretaker(Employee caretaker) {
        this.caretaker = caretaker;
        if (caretaker != null) {
            this.hasCaretaker = true;
        } else {
            this.hasCaretaker = false;
        }
    }

    public void setHasCaretaker(boolean hasCaretaker) {
        this.hasCaretaker = hasCaretaker;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setDiseaseDiary(DiseaseDiary diseaseDiary) {
        this.diseaseDiary = diseaseDiary;
    }

    public void setWasBorn(boolean wasBorn) {
        this.wasBorn = wasBorn;
        this.wasBought = !wasBorn;
    }

    public void setWasBought(boolean wasBought) {
        this.wasBought = wasBought;
        this.wasBorn = !wasBought;
    }

    public void setDead(boolean dead) {
        this.isDead = dead;
    }

    public void setWasSold(boolean wasSold) {
        this.wasSold = wasSold;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }


    public void addDisease(String disease, LocalDate date) {
        if (diseases == null) {
            diseases = new String[0];
        }

        diseases = Arrays.copyOf(diseases, diseases.length + 1);
        diseases[diseases.length - 1] = disease;

        diseaseDiary.addDisease(disease, date);
    }
}
