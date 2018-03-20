package ru.vereshchakov.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vereshchakov.booking.controller.request.TimeSlotRequest;
import ru.vereshchakov.booking.controller.response.Response;
import ru.vereshchakov.booking.controller.response.ResponseStatus;
import ru.vereshchakov.booking.entity.slots.SlotType;
import ru.vereshchakov.booking.entity.slots.TimeSlot;
import ru.vereshchakov.booking.service.Period;
import ru.vereshchakov.booking.service.ScheduleService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dvere on 07.01.2018.
 */
@Component
@Path("/schedule")
@Produces(MediaType.APPLICATION_JSON)
public final class ScheduleController {

    private final String APPLICATION_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    @Qualifier("scheduleServiceImpl")
    private ScheduleService scheduleService;

    @GET
    public Response getAllTimeslotsByMonth(@QueryParam("id") String inId,
                                           @QueryParam("month") String inMonth) {
        //TODO: we have to check input params
        try {
            Month month = Month.valueOf(inMonth.toUpperCase());
            List<TimeSlot> timeSlots = scheduleService.getAllTimeSlotByMonth(inId, month);
            return Response.createResponse()
                    .withStatus(ResponseStatus.OK)
                    .withEntity(timeSlots)
                    .build();
        } catch (IllegalArgumentException e) {
            return createErrorResponse(e);
        }
    }

    @GET
    @Path("/for_period")
    public Response getAllTsByEndDate(@QueryParam("id") String inId,
                                      @QueryParam("end_date") String inEndDate,
                                      @DefaultValue("WEEK") @QueryParam("period") String inPeriod) {
        //TODO: we have to check input params
        try {
            LocalDateTime parsed = LocalDateTime.parse(inEndDate);
            Period period = (inPeriod != null) ? Period.valueOf(inPeriod) : Period.WEEK;
            List<TimeSlot> timeSlots = scheduleService.getTimeSlotsByEndDate(inId, parsed, period);
            Map<String, String> map = new HashMap<>();
            for (Map.Entry<String, String> pair : map.entrySet()) {

            }
            return Response.createResponse()
                    .withStatus(ResponseStatus.OK)
                    .withEntity(timeSlots)
                    .build();

        } catch (DateTimeParseException | IllegalArgumentException e) {
            //TODO: add logic if incorrect date format will be received
            return createErrorResponse(e);
        }
    }

    @POST
    @Path("/add_available_ts")
    @Consumes(APPLICATION_JSON)
    public Response addAvailableTimeSlot(TimeSlotRequest request) {
        List<String> errors = checkTimeSlotRequest(request);
        if (!errors.isEmpty()) {
            return Response.createResponse()
                    .withStatus(ResponseStatus.ERROR)
                    .withEntity(errors)
                    .withErrorMessage("One or more fields isn't filled")
                    .build();
        }

        boolean isAdded = scheduleService.addAvailableTimeSlots(request.getPersonId(), getLdtFromTimeSlotRequest(request));
        return Response.createResponse()
                .withStatus(ResponseStatus.OK)
                .withEntity(isAdded)
                .build();

    }

    private List<String> checkTimeSlotRequest(TimeSlotRequest request) {
        List<String> errors = new ArrayList<>();
        if (request.getPersonId() == null) {
            errors.add("PersonId have to be not null");
        }
        if (request.getSlotType() != SlotType.AVAILABLE) {
            errors.add("Method accept only Available timeslots");
        }
        if (request.getSlotDateTime() == null) {
            errors.add("slotDateTime have to be not null");
        }

        return errors;
    }

    private List<LocalDateTime> getLdtFromTimeSlotRequest(TimeSlotRequest request) {
        List<TimeSlotRequest.SlotDateTime> slotDateTimes = request.getSlotDateTime();
        List<LocalDateTime> result = new ArrayList<>();
        for (TimeSlotRequest.SlotDateTime slotDateTime : slotDateTimes) {
            int year = Integer.parseInt(slotDateTime.getYear());
            int dayOfMonth = Integer.parseInt(slotDateTime.getDayOfMonth());
            int hour = Integer.parseInt(slotDateTime.getHour());
            int minute = Integer.parseInt(slotDateTime.getMinute());

            result.add(LocalDateTime.of(year, slotDateTime.getMonth(), dayOfMonth, hour, minute));
        }

        return result;
    }

    private Response createErrorResponse(Exception e) {
        return Response.createResponse()
                .withStatus(ResponseStatus.ERROR)
                .withErrorMessage(e.getMessage())
                .build();
    }
}
