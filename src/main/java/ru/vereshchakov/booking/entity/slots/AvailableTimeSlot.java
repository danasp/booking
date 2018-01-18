package ru.vereshchakov.booking.entity.slots;

import ru.vereshchakov.booking.entity.person.Person;

import java.time.LocalDateTime;

/**
 * Created by dvere on 07.01.2018.
 */
public final class AvailableTimeSlot extends TimeSlot {

    public AvailableTimeSlot(Person owner, LocalDateTime dateTime) {
        this(owner, dateTime, null);
    }

    public AvailableTimeSlot(Person owner, LocalDateTime dateTime, SlotDivision slotDivision) {
        super(owner, dateTime, slotDivision);
    }

    @Override
    public SlotType getSlotType() {
        return SlotType.AVAILABLE;
    }
}
