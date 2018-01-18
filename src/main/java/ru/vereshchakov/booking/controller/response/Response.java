package ru.vereshchakov.booking.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by dvere on 12.01.2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private final ResponseStatus status;
    private final Object entity;
    private final String errorMessage;


    private Response(ResponseBuilder responseBuilder) {
        this.status = responseBuilder.status;
        this.entity = responseBuilder.entity;
        this.errorMessage = responseBuilder.errorMessage;
    }

    public static ResponseBuilder createResponse() {
        return Response.ResponseBuilder.getInstance();
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public Object getEntity() {
        return entity;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    //Builder
    public static class ResponseBuilder {
        private ResponseStatus status;
        private Object entity;
        private String errorMessage;

        private ResponseBuilder() {}

        public static ResponseBuilder getInstance() {
            return new ResponseBuilder();
        }

        public ResponseBuilder withStatus(ResponseStatus status) {
            this.status = status;
            return this;
        }

        public ResponseBuilder withEntity(Object entity) {
            this.entity = entity;
            return this;
        }

        public ResponseBuilder withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }
}
