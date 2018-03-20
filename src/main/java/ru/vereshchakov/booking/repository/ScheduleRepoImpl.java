package ru.vereshchakov.booking.repository;

import org.springframework.stereotype.Repository;
import ru.vereshchakov.booking.entity.slots.TimeSlot;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

/**
 * Created by dvere on 07.01.2018.
 */

@Repository(value = "personRepoImpl")
public class ScheduleRepoImpl implements ScheduleRepo {

    @Override
    public List<TimeSlot> getAllTimeSlotsByMonth(String personId, Month month) {
        return null;
    }

    @Override
    public List<TimeSlot> getTimeSlotsByEndDate(String personId, LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public boolean addAvailableTimeSlot(String ownerId, LocalDateTime dateTime) {
        return addAvailableTimeSlots(ownerId, Collections.singletonList(dateTime));
    }

    @Override
    public boolean addAvailableTimeSlots(String ownerId, List<LocalDateTime> dateTimes) {
        return false;
    }
}
