package ru.vereshchakov.booking.entity.slots;

import ru.vereshchakov.booking.entity.person.Person;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Created by dvere on 07.01.2018.
 */
public abstract class TimeSlot implements Comparable<TimeSlot> {

    final Person owner;
    final LocalDateTime dateTime;
    final SlotDivision slotDivision;

    public abstract SlotType getSlotType();

    public TimeSlot(Person owner, LocalDateTime dateTime) {
        this(owner, dateTime, null);
    }

    public TimeSlot(Person owner, LocalDateTime dateTime, SlotDivision slotDivision) {
        this.owner = owner;
        this.dateTime = dateTime;
        this.slotDivision = (slotDivision != null)
                ? slotDivision
                : SlotDivision.HOUR;
    }

    public int getYear() {
        return dateTime.getYear();
    }

    public Month getMonth() {
        return dateTime.getMonth();
    }

    public int getDayOfMonth() {
        return dateTime.getDayOfMonth();
    }

    public int getHour() {
        return dateTime.getHour();
    }

    public int getMinute() {
        return dateTime.getMinute();
    }

    @Override
    public int compareTo(TimeSlot o) {
        int dateComparison = this.dateTime.compareTo(o.dateTime);
        if (dateComparison == 0) {
            return this.owner.compareTo(o.owner);
        }

        return dateComparison;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSlot)) return false;

        TimeSlot timeSlot = (TimeSlot) o;

        return owner.equals(timeSlot.owner) && dateTime.equals(timeSlot.dateTime);

    }

    @Override
    public int hashCode() {
        int result = owner.hashCode();
        result = 31 * result + dateTime.hashCode();
        return result;
    }
}

