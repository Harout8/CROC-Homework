package ru.croc.java.winter.school.zoo.tracking.event;


import ru.croc.java.winter.school.zoo.tracking.workshift.WorkShift;


/**
 * Событие прихода сотрудника на работу.
 */
public class EmployeeCameToWorkEvent extends TrackingEvent {
    private final WorkShift workShift;

    public EmployeeCameToWorkEvent(WorkShift workShift) {
        super(workShift.getStartTime());
        this.workShift = workShift;
    }

    public WorkShift getWorkShift() {
        return workShift;
    }
}
