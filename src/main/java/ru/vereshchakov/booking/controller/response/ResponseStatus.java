package ru.vereshchakov.booking.controller.response;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by dvere on 12.01.2018.
 */
public enum ResponseStatus {
    OK("ok"),
    ERROR("error");

    private String status;

    ResponseStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
