package ru.vereshchakov.booking.entity.slots;

import ru.vereshchakov.booking.entity.person.Person;

import java.time.LocalDateTime;

/**
 * Created by dvere on 07.01.2018.
 */
public final class OccupiedTimeSlot extends TimeSlot {
    private final Person consumer; //who occupy this slot

    public OccupiedTimeSlot(Person owner, LocalDateTime dateTime) {
        this(owner, dateTime, null, null);
    }

    public OccupiedTimeSlot(Person owner, LocalDateTime dateTime, SlotDivision slotDivision) {
        this(owner, dateTime, slotDivision, null);
    }

    public OccupiedTimeSlot(Person owner, LocalDateTime dateTime, Person consumer) {
        this(owner, dateTime, null, consumer);
    }

    public OccupiedTimeSlot(Person owner, LocalDateTime dateTime, SlotDivision slotDivision, Person consumer) {
        super(owner, dateTime, slotDivision);
        this.consumer = consumer;
    }

    public Person getConsumer() {
        return consumer;
    }

    @Override
    public SlotType getSlotType() {
        return SlotType.OCCUPIED;
    }
}
