package ru.vereshchakov.booking.controller.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.vereshchakov.booking.entity.slots.SlotType;

import java.time.Month;
import java.util.Collections;
import java.util.List;

/**
 * Created by dvere on 14.01.2018.
 */
@JsonDeserialize(using = TimeSlotRequestDeserializer.class)
public class TimeSlotRequest {

    private String personId;
    private SlotType slotType;
    private List<SlotDateTime> slotDateTime;


    public TimeSlotRequest(String personId, SlotType slotType, List<SlotDateTime> slotDateTime) {
        this.personId = personId;
        this.slotType = slotType;
        this.slotDateTime = slotDateTime;
    }

    public static class SlotDateTime {
        private String year;
        private Month month;
        private String dayOfMonth;
        private String hour;
        private String minute;

        private SlotDateTime() {}

        public static SlotDateTime createSlotDateTime(String year, Month month, String dayOfMonth, String hour, String minute) {
            SlotDateTime slotDateTime = new SlotDateTime();
            slotDateTime.year = year;
            slotDateTime.month = month;
            slotDateTime.dayOfMonth = dayOfMonth;
            slotDateTime.hour = hour;
            slotDateTime.minute = minute;
            return slotDateTime;
        }

        public String getYear() {
            return year;
        }

        public Month getMonth() {
            return month;
        }

        public String getDayOfMonth() {
            return dayOfMonth;
        }

        public String getHour() {
            return hour;
        }

        public String getMinute() {
            return minute;
        }
    }

    public String getPersonId() {
        return personId;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public List<SlotDateTime> getSlotDateTime() {
        return Collections.unmodifiableList(slotDateTime);
    }
}
