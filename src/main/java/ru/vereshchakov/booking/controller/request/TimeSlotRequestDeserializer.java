package ru.vereshchakov.booking.controller.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.vereshchakov.booking.entity.slots.SlotType;

import java.io.IOException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvere on 14.01.2018.
 */
public class TimeSlotRequestDeserializer extends JsonDeserializer<TimeSlotRequest> {

    @Override
    public TimeSlotRequest deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);

        JsonNode personIdJn = (node.get("personId"));
        String personId = (personIdJn == null) ? null : personIdJn.asText();

        JsonNode slotTypeJn = node.get("slotType");
        String slotType = (slotTypeJn == null) ? null : slotTypeJn.asText();

        ArrayNode slotDateTimeArray = (ArrayNode) node.get("slotDateTime");

        List<TimeSlotRequest.SlotDateTime> slotDateTimes = new ArrayList<>();
        for (JsonNode slotDateTimeObj : slotDateTimeArray) {
            ObjectNode dateTimeObj = (ObjectNode) slotDateTimeObj;

            JsonNode yearJn = dateTimeObj.get("year");
            String year = (yearJn == null) ? null : yearJn.asText();

            JsonNode monthJn = dateTimeObj.get("month");
            String month = (monthJn == null) ? null : monthJn.asText();

            JsonNode dayOfMonthJn = dateTimeObj.get("dayOfMonth");
            String dayOfMonth = (dayOfMonthJn == null) ? null : dayOfMonthJn.asText();

            JsonNode hourJn = dateTimeObj.get("hour");
            String hour = (hourJn == null) ? null : hourJn.asText();

            JsonNode minuteJn = dateTimeObj.get("minute");
            String minute = (minuteJn == null) ? null : minuteJn.asText();

            TimeSlotRequest.SlotDateTime slotDateTime = TimeSlotRequest.SlotDateTime.createSlotDateTime(
                    year,
                    (month == null) ? null : Month.valueOf(month.toUpperCase()),
                    dayOfMonth,
                    hour,
                    minute);
            slotDateTimes.add(slotDateTime);
        }


        return new TimeSlotRequest(
                personId,
                (slotType == null) ? null : SlotType.valueOf(slotType.toUpperCase()),
                slotDateTimes
        );
    }
}
