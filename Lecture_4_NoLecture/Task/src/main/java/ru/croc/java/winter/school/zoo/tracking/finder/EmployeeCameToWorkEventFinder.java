package ru.croc.java.winter.school.zoo.tracking.finder;


import ru.croc.java.winter.school.zoo.ZooTerritory;
import ru.croc.java.winter.school.zoo.employee.Employee;
import ru.croc.java.winter.school.zoo.tracking.Tracked;
import ru.croc.java.winter.school.zoo.tracking.event.EmployeeCameToWorkEvent;
import ru.croc.java.winter.school.zoo.tracking.event.TrackingEvent;
import ru.croc.java.winter.school.zoo.tracking.location.Position;
import ru.croc.java.winter.school.zoo.tracking.workshift.WorkShift;

import java.time.LocalDateTime;
import java.util.*;


/**
 * Анализатор события {@link EmployeeCameToWorkEvent}.
 */
public class EmployeeCameToWorkEventFinder implements EventFinder {
    /** Список незавершенных смен для каждого сотрудника. */
    private final Map<String, List<WorkShift>> workShifts = new HashMap<>();

    private final ZooTerritory zooTerritory;


    /**
     * Анализатор события {@link EmployeeCameToWorkEvent}.
     *
     * @param zooTerritory территория зоопарка
     */
    public EmployeeCameToWorkEventFinder(ZooTerritory zooTerritory) {
        this.zooTerritory = zooTerritory;
    }


    @Override
    public List<EmployeeCameToWorkEvent> findNext(Tracked updatedTracked, Map<String, Tracked> trackable) {
        final List<EmployeeCameToWorkEvent> newEvents = new ArrayList<>();

        final EmployeeCameToWorkEvent event = findEmployeeCameToWorkEvent(updatedTracked);
        if (event != null) {
            newEvents.add(event);
        }

        return newEvents;
    }

    /**
     * Событие прихода сотрудника на работу
     *
     * @param updatedTracked сотрудник
     */
    private EmployeeCameToWorkEvent findEmployeeCameToWorkEvent(Tracked updatedTracked) {
        final boolean isEmployee = updatedTracked instanceof Employee;

        if (!isEmployee) {
            return null;
        }

        if (zooTerritory.insideTheZoo(updatedTracked.getCurrentLocation().position)) {
            if (updatedTracked.getLocations().size() > 1) {
                if (!zooTerritory.insideTheZoo(updatedTracked.getLocations().get(updatedTracked.getLocations().size() - 2).position)) {
                    // Сотрудник только пришёл на работу, генерируем событие и добавляем отслеживание завершения
                    final WorkShift workShift = new WorkShift(updatedTracked, LocalDateTime.now());
                    addWorkShift(workShift);
                    return new EmployeeCameToWorkEvent(workShift);
                }
            } else {
                // Впервые появились сведения о местоположении сотрудника и он на работе
                // генерируем событие и добавляем отслеживание завершения
                final WorkShift workShift = new WorkShift(updatedTracked, LocalDateTime.now());
                addWorkShift(workShift);
                return new EmployeeCameToWorkEvent(workShift);
            }
        } else if ((!zooTerritory.insideTheZoo(updatedTracked.getCurrentLocation().position))
                && (updatedTracked.getLocations().size() > 1)
                && (zooTerritory.insideTheZoo(updatedTracked.getLocations().get(updatedTracked.getLocations().size() - 2).position))) {
            // Смена закончилась, событие уже было, но проставим дату завершения и удалим с отслеживания
            final WorkShift workShift = getNotCompletedIWorkShiftOfEmployee(updatedTracked);
            workShift.setFinishTime(LocalDateTime.now());
            removeWorkShift(workShift);
            return null;
        }

        return null;
    }


    private WorkShift getNotCompletedIWorkShiftOfEmployee(Tracked updatedTracked) {
        final List<WorkShift> employeesWorkShifts = workShifts.getOrDefault(updatedTracked.getId(), Collections.emptyList());

        for (WorkShift workShift : employeesWorkShifts) {
            if (workShift.getFinishTime() == null) {
                return workShift;
            }
        }

        return null;
    }

    /**
     * Добавляет смену для отслеживнаия завершения.
     *
     * @param workShift смена
     */
    private void addWorkShift(WorkShift workShift) {
        if (!workShifts.containsKey(workShift.getEmployee().getId())) {
            workShifts.put(workShift.getEmployee().getId(), new ArrayList<>());
        }

        workShifts.get(workShift.getEmployee().getId()).add(workShift);
    }

    /**
     * Удаляет смену для прекращения отслеживнаия завершения.
     *
     * @param workShift смена
     */
    private void removeWorkShift(WorkShift workShift) {
        workShifts.get(workShift.getEmployee().getId()).remove(workShift);
    }
}
