package ru.croc.java.winter.school.zoo.tracking;


import ru.croc.java.winter.school.zoo.ZooTerritory;
import ru.croc.java.winter.school.zoo.animal.Animal;
import ru.croc.java.winter.school.zoo.employee.Employee;
import ru.croc.java.winter.school.zoo.tracking.event.EmployeeAndAnimalInteractionEvent;
import ru.croc.java.winter.school.zoo.tracking.event.TrackingEvent;
import ru.croc.java.winter.school.zoo.tracking.finder.EmployeeAndAnimalInteractionEventFinder;
import ru.croc.java.winter.school.zoo.tracking.finder.EmployeeAndEmployeeInteractionEventFinder;
import ru.croc.java.winter.school.zoo.tracking.finder.EmployeeCameToWorkEventFinder;
import ru.croc.java.winter.school.zoo.tracking.finder.EventFinder;
import ru.croc.java.winter.school.zoo.tracking.interaction.Interaction;
import ru.croc.java.winter.school.zoo.tracking.interaction.InteractionDuration;
import ru.croc.java.winter.school.zoo.tracking.location.Position;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Сервис отслеживания {@link Tracked}.
 */
public class TrackingService {
    /** Отслеживаемые объекты. ид -> объект. */
    private final Map<String, Tracked> trackable;

    /** Журнал событий. */
    private final List<TrackingEvent> events;

    /** Анализаторы событий. */
    private final List<EventFinder> eventFinders;

    /** Территория зоопарка */
    private ZooTerritory zooTerritory;


    public TrackingService(ZooTerritory zooTerritory) {
        trackable = new HashMap<>();
        events = new ArrayList<>();
        eventFinders = new ArrayList<>();

        eventFinders.add(new EmployeeAndAnimalInteractionEventFinder(1));
        eventFinders.add(new EmployeeAndEmployeeInteractionEventFinder(1));

        this.zooTerritory = zooTerritory;
        eventFinders.add(new EmployeeCameToWorkEventFinder(zooTerritory));
    }


    /**
     * Добавляем новый объект для отслеживания.
     *
     * @param tracked новый объект
     */
    public void add(Tracked tracked) {
        trackable.put(tracked.getId(), tracked);
    }

    /**
     * Пришли данные с GPS-датчика (обработанные).
     *
     * @param id ид отсл. объекта
     * @param x Х
     * @param y Y
     */
    public void update(String id, double x, double y) {
        if (!trackable.containsKey(id)) {
            return;
        }

        trackable.get(id).updatePosition(x, y);
        for (EventFinder eventFinder : eventFinders) {
            events.addAll(eventFinder.findNext(trackable.get(id), trackable));
        }
    }

    /**
     * Снимаем слежение с объекта.
     *
     * @param tracked объект
     */
    public void remove(Tracked tracked) {
        trackable.remove(tracked);
    }

    /**
     * Получить общее время, проведённое сотрудником с его подопечными
     * @param employee сотрудник
     *
     * @return общее время, проведённое сотрудником с его подопечными
     */
    public String timeWithWards(Employee employee) {
        String time = "";

        int years = 0;
        int months = 0;
        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        for (TrackingEvent event : events) {
            if (event instanceof EmployeeAndAnimalInteractionEvent) {
                EmployeeAndAnimalInteractionEvent eventEA = (EmployeeAndAnimalInteractionEvent) event;
                if ((eventEA.getInteraction().getA() instanceof Animal
                        && employee.getAnimals().contains(eventEA.getInteraction().getA()))
                    | (eventEA.getInteraction().getB() instanceof Animal
                        && employee.getAnimals().contains(eventEA.getInteraction().getB()))) {

                    InteractionDuration interactionDuration = eventEA.getInteraction().interactionDuration();
                    years += interactionDuration.getYears();
                    months += interactionDuration.getMonths();
                    days += interactionDuration.getDays();
                    hours += interactionDuration.getHours();
                    minutes += interactionDuration.getMinutes();
                    seconds += interactionDuration.getSeconds();
                }
            }
        }

        return (years + " years, "
                + months + " months, "
                + days + " days, "
                + hours + " hours, "
                + minutes + " minutes, "
                + seconds + " seconds");
    }

    /**
     * Этот метод позволяет получить информацию о том, сколько раз сотрудник выводил животных из зоопарка
     * @param employee сотрудник
     *
     * @return кол-во выводов сотрудником животных из зоопарка
     */
    public int howManyTimesGotAnimalsOutFromZoo(Employee employee) {
        int amount = 0;

        for (TrackingEvent event : events) {
            if (event instanceof EmployeeAndAnimalInteractionEvent) {
                Interaction interactionEA = ((EmployeeAndAnimalInteractionEvent) event).getInteraction();

                if (interactionEA.getA().equals(employee)) {
                    for (Position position : interactionEA.getBPositions()) {
                        if (!zooTerritory.insideTheZoo(position)) {
                            amount++;
                        }
                    }
                } else if (interactionEA.getB().equals(employee)) {
                    for (Position position : interactionEA.getAPositions()) {
                        if (!zooTerritory.insideTheZoo(position)) {
                            amount++;
                        }
                    }
                }
            }
        }

        return amount;
    }


    public Set<Tracked> getTrackable() {
        return new HashSet<>(trackable.values());
    }

    public List<TrackingEvent> getEvents() {
        return events;
    }

    public ZooTerritory getZooTerritory() {
        return zooTerritory;
    }

    public void setZooTerritory(ZooTerritory zooTerritory) {
        this.zooTerritory = zooTerritory;
    }
}
