package ru.croc.java.winter.school.zoo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.java.winter.school.zoo.animal.Animal;
import ru.croc.java.winter.school.zoo.animal.IllnessRecord;
import ru.croc.java.winter.school.zoo.employee.Employee;
import ru.croc.java.winter.school.zoo.eventsrecords.ContactRecord;
import ru.croc.java.winter.school.zoo.eventsrecords.EmployeeComeAndLeaveRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ZooTest {

    // Сотрудники
    final Employee bob = new Employee("Боб", LocalDate.of(1980, 3, 1));
    final Employee alise = new Employee("Алиса", LocalDate.of(1987, 7, 1));

    // Животные
    final Animal elephant = new Animal("Слон", LocalDate.now());
    final Animal monkey = new Animal("Обезьяна", LocalDate.now());

    final Zoo zoo = new Zoo("Африка рядом");


    @BeforeEach
    public void setUp() {
        zoo.add(bob, alise);

        zoo.add(elephant, bob);
        zoo.add(monkey, alise);

        monkey.add(new IllnessRecord("Грипп", LocalDateTime.now(), "-"));
    }



    @DisplayName("Обновление местоположений")
    @Test
    public void updateLocationsTest() {
        zoo.updateLocations();

        //System.out.println(monkey.getMovementRecords().get(0).getX());
        //System.out.println(monkey.getMovementRecords().get(0).getY());
        Assertions.assertEquals(98, monkey.getMovementRecords().get(0).getX());
        Assertions.assertEquals(98, monkey.getMovementRecords().get(0).getY());

        //System.out.println(alise.getMovementRecords().get(0).getX());
        //System.out.println(alise.getMovementRecords().get(0).getY());
        Assertions.assertEquals(102, alise.getMovementRecords().get(0).getX());
        Assertions.assertEquals(102, alise.getMovementRecords().get(0).getY());
    }



    @DisplayName("Определение контакта сотрудников и животных")
    @Test
    public void contactRecordsTest() {
        zoo.updateLocations();

        // Expected
        List<ContactRecord> expContactRecords = zoo.getContactRecords();

        // Actual
        List<ContactRecord> actContactRecords = new ArrayList<>();
        actContactRecords.add(new ContactRecord("Боб",
                "Слон",
                101,
                101,
                99,
                99,
                LocalDateTime.of(2020, 3, 30, 20, 0, 0),
                LocalDateTime.of(2020, 3, 30, 20, 15, 0)));

        Assertions.assertEquals(expContactRecords.get(0).toString(), actContactRecords.get(0).toString());
    }



    @DisplayName("Определение прихода и ухода сотрудников")
    @Test
    public void EmployeeComeAndLeaveRecordTest() {
        alise.setNewCoordinates(-1, -1);
        alise.setNewCoordinates(0, 0);
        alise.setNewCoordinates(1, 1);
        alise.setNewCoordinates(0, 0);
        alise.setNewCoordinates(-1, -1);


        // Expected
        String info = "";
        for (EmployeeComeAndLeaveRecord record : alise.getEmployeeComeAndLeaveRecords()) {
            info += record.toString();
        }


        // Actual
        EmployeeComeAndLeaveRecord actualRecord_come = new EmployeeComeAndLeaveRecord("Алиса",
                LocalDateTime.of(2020, 3, 30, 21, 55, 55),
                "Приход");

        EmployeeComeAndLeaveRecord actualRecord_leave = new EmployeeComeAndLeaveRecord("Алиса",
                LocalDateTime.of(2020, 3, 30, 21, 55, 55),
                "Уход");

        List<EmployeeComeAndLeaveRecord> actualRecords = new ArrayList<>();
        actualRecords.add(actualRecord_come);
        actualRecords.add(actualRecord_leave);

        String info_actual = "";
        for (EmployeeComeAndLeaveRecord actualRecord : actualRecords) {
            info_actual += actualRecord.toString();
        }


        // Checking
        Assertions.assertEquals(info, info_actual);
    }
}
