package ru.croc.java.winter.school.zoo.tracking.finder;


import ru.croc.java.winter.school.zoo.animal.Animal;
import ru.croc.java.winter.school.zoo.employee.Employee;
import ru.croc.java.winter.school.zoo.tracking.Tracked;
import ru.croc.java.winter.school.zoo.tracking.event.EmployeeAndAnimalInteractionEvent;
import ru.croc.java.winter.school.zoo.tracking.interaction.Interaction;
import ru.croc.java.winter.school.zoo.tracking.location.Position;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Анализатор события {@link EmployeeAndAnimalInteractionEvent}.
 */
public class EmployeeAndAnimalInteractionEventFinder extends InteractionFinder implements EventFinder  {
    private final double interactionDistance;


    /**
     * Анализатор события {@link EmployeeAndAnimalInteractionEvent}.
     *
     * @param interactionDistance максимальное растояние, которое считается взаимодействием
     */
    public EmployeeAndAnimalInteractionEventFinder(double interactionDistance) {
        this.interactionDistance = interactionDistance;
    }


    @Override
    public List<EmployeeAndAnimalInteractionEvent> findNext(Tracked updatedTracked, Map<String, Tracked> trackable) {
        final List<EmployeeAndAnimalInteractionEvent> newEvents = new ArrayList<>();

        for (Tracked trackedA : trackable.values()) {
            if (trackedA == updatedTracked || trackedA.getCurrentLocation() == null) {
                continue;
            }

            final EmployeeAndAnimalInteractionEvent event = findInteractionEvent(updatedTracked, trackedA);
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
    private EmployeeAndAnimalInteractionEvent findInteractionEvent(Tracked trackedA, Tracked trackedB) {
        final boolean animalAndEmployee = (trackedA instanceof Animal) && (trackedB instanceof Employee)
                || (trackedA instanceof Employee) && (trackedB instanceof Animal);

        if (!animalAndEmployee) {
            return null;
        }

        //
        final boolean isCurrentInteraction = isInteraction(
                trackedA.getCurrentLocation().position,
                trackedB.getCurrentLocation().position,
                interactionDistance
        );

        if (isCurrentInteraction) {
            if (isExistsNotCompletedInteractionBetween(trackedA, trackedB)) {
                Interaction currentInteraction = getNotCompletedInteractionBetween(trackedA, trackedB);
                currentInteraction.getAPositions().add(trackedA.getCurrentLocation().position);
                currentInteraction.getBPositions().add(trackedB.getCurrentLocation().position);

                return null; // взаимодействие уже было зафиксировано и не прекратилось
            }

            // Взаимодействие новое, генерируем событие и добавляем отслеживание завершения
            final Interaction interaction = new Interaction(trackedA, trackedB, LocalDateTime.now());
            addInteraction(interaction);
            return new EmployeeAndAnimalInteractionEvent(interaction);
        } else {
            if (!isExistsNotCompletedInteractionBetween(trackedA, trackedB)) {
                return null; // взаимодействия не было и нет
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
