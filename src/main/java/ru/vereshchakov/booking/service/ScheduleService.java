package ru.vereshchakov.booking.service;

import ru.vereshchakov.booking.entity.slots.TimeSlot;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/**
 * Created by dvere on 07.01.2018.
 */
public interface ScheduleService {
    List<TimeSlot> getTimeSlotsByEndDate(String personId, LocalDateTime endDate, Period period);
    List<TimeSlot> getAllTimeSlotByMonth(String personId, Month month);
    List<TimeSlot> getOccupiedTimeSlotByMonth(String personId, Month month);
    List<TimeSlot> getFreeTimeSlotByMonth(String personId, Month month);

    boolean addAvailableTimeSlot(String ownerId, LocalDateTime dateTime);
    boolean occupyTimeSlot(String ownerId, String consumerId, LocalDateTime dateTime);
}
