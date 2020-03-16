package main;

import zoo.Zoo;
import zoo.animal.Animal;
import zoo.animal.DiseaseDiary;
import zoo.employee.Employee;

import java.time.LocalDate;

/***
 * @author Khachatryan Haroutyun
 *
 * Task 2:
 * У зоопарка появилась потребность программно управлять уходом за животными. Каждому животному назначается ответственный
 * сотрудник, который обеспечивает за ним уход. Сотрудник может иметь несколько подопечных. В зоопарке случаются
 * следующие события:
 *   ● Прием на работу и увольнение сотрудников
 *   ● Появление(покупка/рождение) и исчезновение животных(продажа/смерть)
 *   ● Болезнь животных
 * Программа должна позволять следующие возможности:
 *   ● Добавлять данные по животным и сотрудникам
 *   ● Отслеживать, что у каждого животного будет сотрудник ухаживающий за ним(нельзя уволить сотрудника, не сняв с него
 *     ответственность за животных)
 *   ● Добавлять записи о болезнях животных в дневник болезней
 * Требуется описать структуру классов(зоопарк, сотрудник, животное и дневник болезней), которая обеспечит зоопарк
 * требованиями описанными выше. Для проверки работы в методе main показать ваш демо-сценарий использования программы.
 */

public class MainClass {

    public static void main(String[] args) {

        System.out.println("\n");

        // Создание зоопарка
        Zoo zoo = new Zoo("Central Park");


        // Создание животных:

        Animal tiger = new Animal("Tiger");
        tiger.setZoo(zoo);
        tiger.setWasBorn(true);
        DiseaseDiary diseaseDiary_tiger = new DiseaseDiary(tiger);
        tiger.setDiseaseDiary(diseaseDiary_tiger);

        Animal tigress = new Animal("Tigress", zoo);
        DiseaseDiary diseaseDiary_tigress = new DiseaseDiary(tigress);

        Animal bear = new Animal("Bear", zoo);
        DiseaseDiary diseaseDiary_bear = new DiseaseDiary(bear);
        bear.setCost(10000);
        bear.setWasBought(true);

        Animal fox = new Animal("Fox", zoo);
        DiseaseDiary diseaseDiary_fox = new DiseaseDiary(fox);
        fox.setCost(5000);
        fox.setWasBought(true);


        // Добавление животных в зоопарк
        zoo.add(tiger);
        zoo.add(tigress, true);
        zoo.add(fox);
        zoo.add(bear);

        // Создание работников:

        Employee emp_1 = new Employee("Izzy");
        emp_1.addWard(tigress);
        zoo.hireEmployee(emp_1);
        emp_1.addWard(tigress);
        zoo.fireEmployee(emp_1);
        emp_1.deleteWard(tigress);
        zoo.fireEmployee(emp_1);
        zoo.hireEmployee(emp_1);
        emp_1.addWard(tigress);

        Employee emp_2 = new Employee("Jackie", zoo);
        emp_2.addWard(tiger);

        Employee emp_3 = new Employee("Vasya", zoo, new Animal[] {bear, fox});


        // Добавление болезней в дненик болезней:

        fox.addDisease("Plague", LocalDate.of(2020, 3, 16));                 // Чума
        fox.addDisease("Tularemia", LocalDate.of(2020, 3, 16));
        bear.addDisease("Joint inflammation", LocalDate.of(2020, 3, 16));   // Воспаление суставов

        // Вывод данных: //

        // Общая информация:

        System.out.print("\n");

        System.out.println("zoo.Zoo title: " + zoo.getTitle());

        System.out.print("zoo.Zoo employees: ");
        for (Employee employee : zoo.getEmployees()) {
            System.out.print(employee.getName() + ". ");
        }

        System.out.print("\n");

        System.out.print("zoo.Zoo animals: ");
        for (Animal animal : zoo.getAnimals()) {
            System.out.print(animal.getTitle() + ". ");
        }

        System.out.println("\n");

        // Болезни животных:
        fox.getDiseaseDiary().showDiseaseInfo();
        bear.getDiseaseDiary().showDiseaseInfo();
        tiger.getDiseaseDiary().showDiseaseInfo();
        tigress.getDiseaseDiary().showDiseaseInfo();

        System.out.print("\n");


        // Подопечные сотрудников зоопарка:

        System.out.print("Wards of " + emp_1.getName() + ": ");
        for (Animal ward : emp_1.getWards()) {
            System.out.print(ward.getTitle() + ". ");
        }
        System.out.print("\n");

        System.out.print("Wards of " + emp_2.getName() + ": ");
        for (Animal ward : emp_2.getWards()) {
            System.out.print(ward.getTitle() + ". ");
        }
        System.out.print("\n");

        System.out.print("Wards of " + emp_3.getName() + ": ");
        for (Animal ward : emp_3.getWards()) {
            System.out.print(ward.getTitle() + ". ");
        }

        System.out.println("\n");


        // Ответственные за животных сотрудники:
        if (tiger.hasCaretaker()) {
            System.out.println("Caretaker of " + tiger.getTitle() + " is " + tiger.getCaretaker().getName());
        } else {
            System.out.println(tiger.getTitle() + " has no caretaker");
        }

        if (tigress.hasCaretaker()) {
            System.out.println("Caretaker of " + tigress.getTitle() + " is " + tigress.getCaretaker().getName());
        } else {
            System.out.println(tigress.getTitle() + " has no caretaker");
        }

        if (bear.hasCaretaker()) {
            System.out.println("Caretaker of " + bear.getTitle() + " is " + bear.getCaretaker().getName());
        } else {
            System.out.println(bear.getTitle() + " has no caretaker !");
        }

        if (fox.hasCaretaker()) {
            System.out.println("Caretaker of " + fox.getTitle() + " is " + fox.getCaretaker().getName());
        } else {
            System.out.println(fox.getTitle() + " has no caretaker");
        }

        System.out.print("\n");


        // Стоимости животных
        System.out.println("Cost of " + tiger.getTitle() + " is " + tiger.getCost());
        System.out.println("Cost of " + tigress.getTitle() + " is " + tigress.getCost());
        System.out.println("Cost of " + bear.getTitle() + " is " + bear.getCost());
        System.out.println("Cost of " + fox.getTitle() + " is " + fox.getCost());

        System.out.print("\n");


        // Как появились животные в зоопарке
        if (tiger.wasBorn()) {
            System.out.println(tiger.getTitle() + " was born in the zoo");
        } else if (tiger.wasBought()) {
            System.out.println(tiger.getTitle() + " was bought for " + tiger.getCost());
        }

        if (tigress.wasBought()) {
            System.out.println(tiger.getTitle() + " was bought for " + tigress.getCost());
        } else if (tigress.wasBorn()) {
            System.out.println(tigress.getTitle() + " was born in the zoo");
        }

        if (bear.wasBorn()) {
            System.out.println(bear.getTitle() + " was born in the zoo");
        } else if (bear.wasBought()) {
            System.out.println(bear.getTitle() + " was bought for " + bear.getCost());
        }

        if (fox.wasBorn()) {
            System.out.println(fox.getTitle() + " was born in the zoo");
        } else if (fox.wasBought()) {
            System.out.println(fox.getTitle() + " was bought for " + fox.getCost());
        }
    }
}
