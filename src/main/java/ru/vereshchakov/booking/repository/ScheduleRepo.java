package ru.vereshchakov.booking.repository;

import ru.vereshchakov.booking.entity.slots.TimeSlot;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/**
 * Created by dvere on 07.01.2018.
 */

public interface ScheduleRepo {
    List<TimeSlot> getAllTimeSlotsByMonth(String personId, Month month);
    List<TimeSlot> getTimeSlotsByEndDate(String personId, LocalDateTime startDate, LocalDateTime endDate);
    boolean addAvailableTimeSlot(String ownerId, LocalDateTime dateTime);
    boolean addAvailableTimeSlots(String ownerId, List<LocalDateTime> dateTimes);
}
