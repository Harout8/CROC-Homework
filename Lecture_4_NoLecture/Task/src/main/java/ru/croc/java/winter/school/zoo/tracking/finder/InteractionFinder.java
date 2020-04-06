package ru.croc.java.winter.school.zoo.tracking.finder;


import ru.croc.java.winter.school.zoo.tracking.Tracked;
import ru.croc.java.winter.school.zoo.tracking.interaction.Interaction;
import ru.croc.java.winter.school.zoo.tracking.location.Position;

import java.util.*;


/**
 * Анализатор события взаимодействия.
 */
public abstract class InteractionFinder {
    /** Список незавершенных взаимодействий для каждого объекта. */
    protected final Map<String, List<Interaction>> interactions = new HashMap<>();


    /**
     * Проверяет находятся ли два обекта в зоне взаимодействия.
     *
     * @param a а
     * @param b b
     * @return true, если взаимодействие есть в текущий момент
     */
    protected boolean isInteraction(Position a, Position b, double interactionDistance) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2)) <= interactionDistance;
    }

    /**
     * Проверяет существует ли незаконченное взаимодействие между двуми объектами.
     *
     * @param a объект A
     * @param b объекта B
     * @return true, если да
     */
    protected boolean isExistsNotCompletedInteractionBetween(Tracked a, Tracked b) {
        return getNotCompletedInteractionBetween(a, b) != null;
    }

    /**
     * Находит незаконченное взаимодействие между двуми объектами.
     *
     * @param a объект A
     * @param b объекта B
     * @return взаимодействие, null иначе
     */
    protected Interaction getNotCompletedInteractionBetween(Tracked a, Tracked b) {
        final List<Interaction> interactionsForA = interactions.getOrDefault(a.getId(), Collections.emptyList());

        for (Interaction interaction : interactionsForA) {
            if (interaction.getA() == b || interaction.getB() == b) {
                return interaction;
            }
        }

        return null;
    }

    /**
     * Добавляет взаимодействие для отслеживнаия завершения.
     *
     * @param interaction взаимодействие
     */
    protected void addInteraction(Interaction interaction) {
        if (!interactions.containsKey(interaction.getA().getId())) {
            interactions.put(interaction.getA().getId(), new ArrayList<>());
        }

        if (!interactions.containsKey(interaction.getB().getId())) {
            interactions.put(interaction.getB().getId(), new ArrayList<>());
        }

        interactions.get(interaction.getA().getId()).add(interaction);
        interactions.get(interaction.getB().getId()).add(interaction);
    }

    /**
     * Удаляет взаимодействие для прекращения отслеживнаия завершения.
     *
     * @param interaction взаимодействие
     */
    protected void removeInteraction(Interaction interaction) {
        interactions.get(interaction.getA().getId()).remove(interaction);
        interactions.get(interaction.getB().getId()).remove(interaction);
    }
}
