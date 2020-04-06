package ru.croc.java.winter.school.zoo.tracking.interaction;


import ru.croc.java.winter.school.zoo.tracking.Tracked;
import ru.croc.java.winter.school.zoo.tracking.location.Position;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Взаимодействие между двумя отслеживаемыми объектами.
 */
public class Interaction {
    private final Tracked a;
    private final Tracked b;

    private List<Position> aPositions;
    private List<Position> bPositions;

    private final LocalDateTime startTime;
    private LocalDateTime finishTime;

    InteractionDuration interactionDuration;


    /**
     * Взаимодействие между двумя отслеживаемыми объектами.
     *
     * @param a первый объект
     * @param b второй объект
     * @param startTime время начала взаимодействия
     */
    public Interaction(Tracked a, Tracked b, LocalDateTime startTime) {
        this.a = a;
        this.b = b;

        aPositions = new ArrayList<>();
        bPositions = new ArrayList<>();

        aPositions.add(a.getCurrentLocation().position);
        bPositions.add(b.getCurrentLocation().position);

        this.startTime = startTime;
        finishTime = null;
    }


    public InteractionDuration interactionDuration() {
        interactionDuration = new InteractionDuration(startTime, finishTime);
        interactionDuration.computeInteractionDuration();

        return interactionDuration;
    }


    /**
     * Время окончания взаимодействия.
     *
     * @return время, null если объекты еще взаимодействуют
     */
    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    /**
     * Устанавливаем время окончания взаимодействия.
     *
     * @param finishTime время окончания
     */
    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public Tracked getA() {
        return a;
    }

    public Tracked getB() {
        return b;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public List<Position> getAPositions() {
        return aPositions;
    }

    public List<Position> getBPositions() {
        return bPositions;
    }
}
