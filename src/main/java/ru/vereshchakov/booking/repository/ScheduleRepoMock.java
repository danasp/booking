package ru.vereshchakov.booking.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.vereshchakov.booking.entity.person.Consumer;
import ru.vereshchakov.booking.entity.person.Person;
import ru.vereshchakov.booking.entity.person.Producer;
import ru.vereshchakov.booking.entity.slots.AvailableTimeSlot;
import ru.vereshchakov.booking.entity.slots.OccupiedTimeSlot;
import ru.vereshchakov.booking.entity.slots.TimeSlot;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by dvere on 07.01.2018.
 */
@Repository(value = "personScheduleRepoMock")
@Primary
public class ScheduleRepoMock implements ScheduleRepo {

    @Override
    public List<TimeSlot> getAllTimeSlotsByMonth(String person, Month month) {
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            timeSlots.addAll(createFreeTimeSlots(month));
            timeSlots.addAll(createFreeTimeSlots(month));
            timeSlots.addAll(createFreeTimeSlots(month));
            timeSlots.addAll(createFreeTimeSlots(month));
            timeSlots.addAll(createOccupiedTimeSlots(month));
            timeSlots.addAll(createOccupiedTimeSlots(month));
            timeSlots.addAll(createOccupiedTimeSlots(month));
            timeSlots.addAll(createOccupiedTimeSlots(month));
            timeSlots.addAll(createFreeTimeSlots(month));
            timeSlots.addAll(createFreeTimeSlots(month));
            timeSlots.addAll(createOccupiedTimeSlots(month));
            timeSlots.addAll(createOccupiedTimeSlots(month));
        }
        return timeSlots;
    }

    @Override
    public List<TimeSlot> getTimeSlotsByEndDate(String personId, LocalDateTime startDate, LocalDateTime endDate) {
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.addAll(createFreeTimeSlots(new Random().nextBoolean() ? startDate : endDate));
        timeSlots.addAll(createFreeTimeSlots(new Random().nextBoolean() ? startDate : endDate));
        timeSlots.addAll(createFreeTimeSlots(new Random().nextBoolean() ? startDate : endDate));
        timeSlots.addAll(createFreeTimeSlots(new Random().nextBoolean() ? startDate : endDate));
        timeSlots.addAll(createOccupiedTimeSlots(new Random().nextBoolean() ? startDate : endDate));
        timeSlots.addAll(createOccupiedTimeSlots(new Random().nextBoolean() ? startDate : endDate));
        timeSlots.addAll(createOccupiedTimeSlots(new Random().nextBoolean() ? startDate : endDate));
        timeSlots.addAll(createOccupiedTimeSlots(new Random().nextBoolean() ? startDate : endDate));
        timeSlots.addAll(createFreeTimeSlots(new Random().nextBoolean() ? startDate : endDate));
        timeSlots.addAll(createFreeTimeSlots(new Random().nextBoolean() ? startDate : endDate));
        timeSlots.addAll(createOccupiedTimeSlots(new Random().nextBoolean() ? startDate : endDate));
        timeSlots.addAll(createOccupiedTimeSlots(new Random().nextBoolean() ? startDate : endDate));
        return timeSlots;
    }

    @Override
    public boolean addAvailableTimeSlot(String ownerId, LocalDateTime dateTime) {
        return addAvailableTimeSlots(ownerId, Collections.singletonList(dateTime));
    }

    @Override
    public boolean addAvailableTimeSlots(String ownerId, List<LocalDateTime> dateTimes) {
        return true;
    }

    private List<TimeSlot> createFreeTimeSlots(LocalDateTime dateTime) {
        List<TimeSlot> timeSlots = new ArrayList<>();

        Person owner = new Producer("1");
        TimeSlot free = new AvailableTimeSlot(owner, dateTime);
        timeSlots.add(free);
        return timeSlots;
    }

    private List<TimeSlot> createOccupiedTimeSlots(LocalDateTime dateTime) {
        List<TimeSlot> timeSlots = new ArrayList<>();
        Person consumer = new Consumer("1");
        Person owner = new Producer("1");
        TimeSlot occupied = new OccupiedTimeSlot(owner, dateTime, consumer);
        timeSlots.add(occupied);
        return timeSlots;
    }

    private List<TimeSlot> createFreeTimeSlots(Month month) {
        List<TimeSlot> timeSlots = new ArrayList<>();

        Person owner = new Producer("1");
        TimeSlot free = new AvailableTimeSlot(owner, createLdtWithCustomMonth(month));
        timeSlots.add(free);
        return timeSlots;
    }

    private List<TimeSlot> createOccupiedTimeSlots(Month month) {
        List<TimeSlot> timeSlots = new ArrayList<>();
        Person consumer = new Consumer("1");
        Person owner = new Producer("1");
        TimeSlot occupied = new OccupiedTimeSlot(owner, createLdtWithCustomMonth(month), consumer);
        timeSlots.add(occupied);
        return timeSlots;
    }

    private LocalDateTime createLdtWithCustomMonth(Month month) {
        LocalDateTime start = LocalDateTime.of(2018, Month.JANUARY, 1, 0, 0);
        LocalDateTime now = LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(start, now);
        LocalDateTime random = start.plusHours(new Random().nextInt((int) hours + 1));
        LocalDateTime searched = LocalDateTime.of(random.getYear(), month, random.getDayOfMonth(), random.getHour(), random.getMinute());
        return searched;
    }
}
