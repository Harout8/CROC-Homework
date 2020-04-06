package ru.croc.java.winter.school.zoo.tracking;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.java.winter.school.zoo.Zoo;
import ru.croc.java.winter.school.zoo.animal.Animal;
import ru.croc.java.winter.school.zoo.employee.Employee;
import ru.croc.java.winter.school.zoo.tracking.event.EmployeeAndAnimalInteractionEvent;
import ru.croc.java.winter.school.zoo.tracking.event.EmployeeAndEmployeeInteractionEvent;
import ru.croc.java.winter.school.zoo.tracking.event.EmployeeCameToWorkEvent;
import ru.croc.java.winter.school.zoo.tracking.interaction.Interaction;
import ru.croc.java.winter.school.zoo.tracking.workshift.WorkShift;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Проверка сервиса отслеживания объектов в зоопарке.
 */
public class TrackingServiceTest {
    private Zoo zoo;
    private Employee bob;
    private Employee Diana;
    private Employee harout;
    private Animal elephant;

    @BeforeEach
    public void init() {
        // Сотрудники
        bob = new Employee("Боб", LocalDate.of(1980, 3, 1));
        Diana = new Employee("Диана", LocalDate.of(1998, 8, 24));
        harout = new Employee("Harout", LocalDate.of(1998, 4, 1));
        final Employee alise = new Employee("Алиса", LocalDate.of(1987, 7, 1));

        // Животные
        elephant = new Animal("Слон", LocalDate.now());
        final Animal monkey = new Animal("Обезьяна", LocalDate.now());

        final Zoo zoo = new Zoo("Симба");
        zoo.add(Diana, harout);
        zoo.add(bob, alise);
        zoo.add(elephant, bob);
        zoo.add(monkey, alise);

        this.zoo = zoo;
    }

    @DisplayName("Проверка журнала отслеживания животных")
    @Test
    public void testJournalOfAnimalTracking() throws InterruptedException {
        final TrackingService trackingService = zoo.getTrackingService();

        final Animal lion = new Animal("Лев", LocalDate.of(1990, 3, 8));
        final Set<Tracked> animalsAndEmployees = new HashSet<>();
        animalsAndEmployees.addAll(zoo.getAnimals());
        animalsAndEmployees.addAll(zoo.getEmployees());
        Assertions.assertEquals(animalsAndEmployees, trackingService.getTrackable());

        Assertions.assertFalse(trackingService.getTrackable().contains(lion));
        zoo.add(lion, bob);
        Assertions.assertTrue(trackingService.getTrackable().contains(lion));

        final LocalDateTime beforeTime = LocalDateTime.now();
        Thread.sleep(1);
        trackingService.update(lion.getId(), 0, 0);
        Thread.sleep(1);
        final LocalDateTime betweenTime = LocalDateTime.now();
        Thread.sleep(1);
        trackingService.update(lion.getId(), 10, 10);
        Thread.sleep(1);
        final LocalDateTime afterTime = LocalDateTime.now();

        Assertions.assertTrue(lion.getLocations().get(0).time.isAfter(beforeTime));
        Assertions.assertTrue(lion.getLocations().get(0).time.isBefore(betweenTime));

        Assertions.assertTrue(lion.getLocations().get(1).time.isAfter(betweenTime));
        Assertions.assertTrue(lion.getLocations().get(1).time.isBefore(afterTime));

    }


    @DisplayName("Проверка отслеживания событий взаимодействия животных")
    @Test
    public void testInteractionEmployeeAndAnimal() {
        final TrackingService trackingService = zoo.getTrackingService();

        // начальные позиции
        trackingService.update(bob.getId(), 0, 0);
        trackingService.update(elephant.getId(), 10, 10);
        Assertions.assertTrue(trackingService.getEvents().isEmpty());

        // Боб подошел к слону
        trackingService.update(bob.getId(), 10, 10);
        Assertions.assertEquals(2, trackingService.getEvents().size());
        final Interaction interaction = ((EmployeeAndAnimalInteractionEvent) trackingService.getEvents().get(0))
                .getInteraction();
        Assertions.assertEquals(interaction.getA(), bob);
        Assertions.assertEquals(interaction.getB(), elephant);
        Assertions.assertNull(interaction.getFinishTime());

        // Боб продолжает стоять рядом со слоном
        trackingService.update(bob.getId(), 10.01, 9.99);
        trackingService.update(elephant.getId(), 9.98, 10.001);
        Assertions.assertEquals(2, trackingService.getEvents().size());
        Assertions.assertNull(interaction.getFinishTime());

        // Слон убежал от Боба
        trackingService.update(elephant.getId(), 5.01, 5.99);
        Assertions.assertEquals(2, trackingService.getEvents().size());
        Assertions.assertNotNull(interaction.getFinishTime());

        // Боб догнал слона
        trackingService.update(bob.getId(), 4.98, 6.02);
        Assertions.assertEquals(3, trackingService.getEvents().size());
    }

    @DisplayName("Проверка события прихода сотрудника на работу")
    @Test
    public void testOfEmployeeComingToWork() {
        final TrackingService trackingService = zoo.getTrackingService();

        // Начальные позиции
        trackingService.update(bob.getId(), -1, -1);
        trackingService.update(elephant.getId(), 10, 10);
        Assertions.assertTrue(trackingService.getEvents().isEmpty());

        // Боб пришёл на работу
        trackingService.update(bob.getId(), 1, 1);
        Assertions.assertEquals(1, trackingService.getEvents().size());
        final WorkShift workShift = ((EmployeeCameToWorkEvent) trackingService.getEvents().get(0)).getWorkShift();
        Assertions.assertEquals(workShift.getEmployee(), bob);
        Assertions.assertNull(workShift.getFinishTime());

        // Боб продолжает работать
        trackingService.update(bob.getId(), 50, 50);
        Assertions.assertEquals(1, trackingService.getEvents().size());
        Assertions.assertNull(workShift.getFinishTime());

        // Боб ушёл с работы
        trackingService.update(bob.getId(), -5, -5);
        Assertions.assertEquals(1, trackingService.getEvents().size());
        Assertions.assertNotNull(workShift.getFinishTime());

        // Боб вернулся на работу
        trackingService.update(bob.getId(), 4, 4);
        Assertions.assertEquals(2, trackingService.getEvents().size());
    }

    @DisplayName("Проверка событий взаимодействия сотрудников")
    @Test
    public void testEmployeesInteraction() {
        final TrackingService trackingService = zoo.getTrackingService();

        // Начальные позиции
        trackingService.update(Diana.getId(), 11, 11);
        trackingService.update(harout.getId(), 22, 22);
        Assertions.assertEquals(2, trackingService.getEvents().size());

        // Harout подошёл к Диане
        trackingService.update(harout.getId(), 11.1, 11.1);
        Assertions.assertEquals(3, trackingService.getEvents().size());

        final Interaction interaction = ((EmployeeAndEmployeeInteractionEvent) trackingService.getEvents().get(2))
                .getInteraction();
        Assertions.assertEquals(interaction.getA(), harout);
        Assertions.assertEquals(interaction.getB(), Diana);
        Assertions.assertNull(interaction.getFinishTime());

        // Harout продолжает стоять рядом с Дианой
        trackingService.update(harout.getId(), 11.01, 11.01);
        trackingService.update(Diana.getId(), 11, 11);
        Assertions.assertEquals(3, trackingService.getEvents().size());
        Assertions.assertNull(interaction.getFinishTime());

        // Harout временно отошёл от Дианы
        trackingService.update(harout.getId(), 98, 98);
        Assertions.assertEquals(3, trackingService.getEvents().size());
        Assertions.assertNotNull(interaction.getFinishTime());

        // Harout вернулся к Диане
        trackingService.update(harout.getId(), 11, 11);
        Assertions.assertEquals(4, trackingService.getEvents().size());
    }

    @DisplayName("Проверка времени, проведённому сотрудником с подопечными")
    @Test
    public void testTimeWithWards() throws InterruptedException {
        final TrackingService trackingService = zoo.getTrackingService();

        // Начальные позиции
        trackingService.update(bob.getId(), 0, 0);
        trackingService.update(elephant.getId(), 10, 10);
        Assertions.assertTrue(trackingService.getEvents().isEmpty());

        // Боб прищёл на работу
        trackingService.update(bob.getId(), 1, 1);
        Assertions.assertEquals(1, trackingService.getEvents().size());

        // Боб подошёл к слону
        trackingService.update(bob.getId(), 10, 10);
        Assertions.assertEquals(2, trackingService.getEvents().size());

        // Боб продолжает стоять рядом со слоном
        trackingService.update(bob.getId(), 10.01, 9.99);
        trackingService.update(elephant.getId(), 9.98, 10.001);
        Thread.sleep(1000);

        // Боб отошёл от слона
        trackingService.update(bob.getId(), 20, 20);
        Assertions.assertEquals(2, trackingService.getEvents().size());

        String time = trackingService.timeWithWards(bob);
        //System.out.println(time);
        Assertions.assertEquals("0 years, 0 months, 0 days, 0 hours, 0 minutes, 1 seconds", time);

        // Боб снова подошёл к слону
        trackingService.update(bob.getId(), 10, 10);

        // Боб продолжает стоять рядом со слоном
        trackingService.update(bob.getId(), 10.01, 9.99);
        trackingService.update(elephant.getId(), 9.98, 10.001);
        Thread.sleep(1000);

        // Боб отошёл от слона
        trackingService.update(bob.getId(), 20, 20);

        String time2 = trackingService.timeWithWards(bob);
        //System.out.println(time2);
        Assertions.assertEquals("0 years, 0 months, 0 days, 0 hours, 0 minutes, 2 seconds", time2);
    }

    @DisplayName("Проверка поиска кол-ва выводов животных из зоопарка сотрудником")
    @Test
    public void testEmployeeGettingAnimalsOutFromTheZoo() {
        final TrackingService trackingService = zoo.getTrackingService();

        // Начальные позиции
        trackingService.update(bob.getId(), 99, 99);
        trackingService.update(elephant.getId(), 99, 99);
        Assertions.assertEquals(0, trackingService.howManyTimesGotAnimalsOutFromZoo(bob));

        // Они вышли из зоопарка
        trackingService.update(bob.getId(), 199, 199);
        trackingService.update(elephant.getId(), 199, 199);
        Assertions.assertEquals(1, trackingService.howManyTimesGotAnimalsOutFromZoo(bob));

        // Они вернулись в зоопарк
        trackingService.update(bob.getId(), 99, 99);
        trackingService.update(elephant.getId(), 99, 99);
        Assertions.assertEquals(1, trackingService.howManyTimesGotAnimalsOutFromZoo(bob));

        // Они снова вышли из зоопарка
        trackingService.update(bob.getId(), 1999, 1999);
        trackingService.update(elephant.getId(), 1999, 1999);
        Assertions.assertEquals(2, trackingService.howManyTimesGotAnimalsOutFromZoo(bob));

        // Они снова вернулись в зоопарк
        trackingService.update(bob.getId(), 99, 99);
        trackingService.update(elephant.getId(), 99, 99);
        Assertions.assertEquals(2, trackingService.howManyTimesGotAnimalsOutFromZoo(bob));
    }
}
