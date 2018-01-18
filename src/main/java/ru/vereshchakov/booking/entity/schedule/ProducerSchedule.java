package ru.vereshchakov.booking.entity.schedule;

import ru.vereshchakov.booking.entity.person.Person;
import ru.vereshchakov.booking.entity.slots.TimeSlot;

import java.util.*;

/**
 * Created by dvere on 14.01.2018.
 */
public class ProducerSchedule {

    private Person scheduleOwner;
    private Set<TimeSlot> usedTimeSlots = new TreeSet<>(); // used == Available or Occupied

    public ProducerSchedule(Person scheduleOwner) {
        this.scheduleOwner = scheduleOwner;
    }

    public void addTimeSlot(TimeSlot timeSlot) {
        usedTimeSlots.add(timeSlot);
    }
}
