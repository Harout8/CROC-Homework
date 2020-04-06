package ru.croc.java.winter.school.zoo.tracking.finder;


import ru.croc.java.winter.school.zoo.employee.Employee;
import ru.croc.java.winter.school.zoo.tracking.Tracked;
import ru.croc.java.winter.school.zoo.tracking.event.EmployeeAndAnimalInteractionEvent;
import ru.croc.java.winter.school.zoo.tracking.event.EmployeeAndEmployeeInteractionEvent;
import ru.croc.java.winter.school.zoo.tracking.event.TrackingEvent;
import ru.croc.java.winter.school.zoo.tracking.interaction.Interaction;
import ru.croc.java.winter.school.zoo.tracking.location.Position;

import java.time.LocalDateTime;
import java.util.*;


/**
 * Анализатор события {@link EmployeeAndEmployeeInteractionEvent}.
 */
public class EmployeeAndEmployeeInteractionEventFinder extends InteractionFinder implements EventFinder {
    private final double interactionDistance;


    /**
     * Анализатор события {@link EmployeeAndAnimalInteractionEvent}.
     *
     * @param interactionDistance максимальное растояние, которое считается взаимодействием
     */
    public EmployeeAndEmployeeInteractionEventFinder(double interactionDistance) {
        this.interactionDistance = interactionDistance;
    }


    @Override
    public List<EmployeeAndEmployeeInteractionEvent> findNext(Tracked updatedTracked, Map<String, Tracked> trackable) {
        final List<EmployeeAndEmployeeInteractionEvent> newEvents = new ArrayList<>();

        for (Tracked trackedA : trackable.values()) {
            if (trackedA == updatedTracked || trackedA.getCurrentLocation() == null) {
                continue;
            }

            final EmployeeAndEmployeeInteractionEvent event = findInteractionEvent(updatedTracked, trackedA);
            if (event != null) {
                newEvents.add(event);
            }
        }

        return newEvents;
    }

    /**
     * Поиск события взаимодействия сотрудника и животного.
     *
     * @param trackedA первый объект
     * @param trackedB второй объект
     */
    private EmployeeAndEmployeeInteractionEvent findInteractionEvent(Tracked trackedA, Tracked trackedB) {
        final boolean employeeAndEmployee = (trackedA instanceof Employee) && (trackedB instanceof Employee);

        if (!employeeAndEmployee) {
            return null;
        }

        //
        final boolean currentInteraction = isInteraction(
                trackedA.getCurrentLocation().position,
                trackedB.getCurrentLocation().position,
                interactionDistance
        );

        if (currentInteraction) {
            if (isExistsNotCompletedInteractionBetween(trackedA, trackedB)) {
                return null;    // взаимодействие уже было зафиксировано и не прекратилось
            }

            // Взаимодействие новое, генерируем событие и добавляем отслеживание завершения
            final Interaction interaction = new Interaction(trackedA, trackedB, LocalDateTime.now());
            addInteraction(interaction);
            return new EmployeeAndEmployeeInteractionEvent(interaction);
        } else {
            if (!isExistsNotCompletedInteractionBetween(trackedA, trackedB)) {
                return null;    // взаимодействия не было и нет
            }

            // взаимодействие прекратилось, событие уже было, но проставим дату завершения
            // и удалим с отслеживания
            final Interaction interaction = getNotCompletedInteractionBetween(trackedA, trackedB);
            interaction.setFinishTime(LocalDateTime.now());
            removeInteraction(interaction);
            return null;
        }
    }
}
