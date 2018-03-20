package ru.vereshchakov.booking.service;

import org.springframework.stereotype.Service;
import ru.vereshchakov.booking.entity.person.Person;
import ru.vereshchakov.booking.entity.slots.AvailableTimeSlot;
import ru.vereshchakov.booking.entity.slots.OccupiedTimeSlot;
import ru.vereshchakov.booking.entity.slots.TimeSlot;
import ru.vereshchakov.booking.repository.PersonRepo;
import ru.vereshchakov.booking.repository.ScheduleRepo;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dvere on 07.01.2018.
 */
@Service(value = "scheduleServiceImpl")
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepo scheduleRepo;
    private PersonRepo personRepo;

    public ScheduleServiceImpl(ScheduleRepo scheduleRepo, PersonRepo personRepo) {
        this.scheduleRepo = scheduleRepo;
        this.personRepo = personRepo;
    }

    @Override
    public List<TimeSlot> getTimeSlotsByEndDate(String personId, LocalDateTime endDate, Period period) {
        List<TimeSlot> timeSlots = scheduleRepo.getTimeSlotsByEndDate(personId, endDate, findStartDate(endDate, period));
        Collections.sort(timeSlots);
        return timeSlots;
    }

    private LocalDateTime findStartDate(LocalDateTime endDate, Period period) {
        switch (period) {
            case MONTH:
                return endDate.minusMonths(1);
            case WEEK:
                return endDate.minusWeeks(1);
            case DAY:
                return endDate.minusDays(1);
            default:
                throw new IllegalArgumentException("Period is not correct - " + period);
        }
    }

    public List<TimeSlot> getAllTimeSlotByMonth(String personId, Month month) {
        List<TimeSlot> allTimeSlotsByMonth = scheduleRepo.getAllTimeSlotsByMonth(personId, month);
        Collections.sort(allTimeSlotsByMonth);
        return allTimeSlotsByMonth;
    }

    @Override
    public List<TimeSlot> getOccupiedTimeSlotByMonth(String personId, Month month) {
        List<TimeSlot> timeSlots = getAllTimeSlotByMonth(personId, month);
        return timeSlots.stream()
                .filter(ts -> ts.getClass() == OccupiedTimeSlot.class)
                .map(ts -> (OccupiedTimeSlot)ts)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeSlot> getFreeTimeSlotByMonth(String personId, Month month) {
        List<TimeSlot> timeSlots = getAllTimeSlotByMonth(personId, month);
        return timeSlots.stream()
                .filter(ts -> ts.getClass() == AvailableTimeSlot.class)
                .map(ts -> (AvailableTimeSlot)ts)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addAvailableTimeSlot(String ownerId, LocalDateTime dateTime) {
        return addAvailableTimeSlots(ownerId, Collections.singletonList(dateTime));
    }

    @Override
    public boolean addAvailableTimeSlots(String ownerId, List<LocalDateTime> dateTimes) {
        Person producer = personRepo.getPersonById(ownerId);
        //TODO: check that timeslot wasn't occupied by customer
        for (LocalDateTime dateTime : dateTimes) {
            if (!isAvailableToBeAvailable(ownerId, dateTime)) {
                return false;
            }
        }

        return scheduleRepo.addAvailableTimeSlots(producer.getId(), dateTimes);
    }

    @Override
    public boolean occupyTimeSlot(String ownerId, String consumerId, LocalDateTime dateTime) {
        return false;
    }

    private boolean isAvailableToBeAvailable(String owner, LocalDateTime dateTime) {
        return true;
    }
}
