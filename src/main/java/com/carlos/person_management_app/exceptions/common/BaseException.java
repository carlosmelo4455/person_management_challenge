package com.carlos.person_management_app.exceptions.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import lombok.Getter;

import java.util.Map;

abstract public class BaseException extends ServletException {

    @Getter
    protected String type;

    protected BaseException(String type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }


    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this.toJsonObject());
        } catch (JsonProcessingException e) {
            e.getMessage();
            return "{}";
        }
    }

    protected Object toJsonObject() {
        return Map.of(
                "message", getMessage(),
                "type", getType()
        );
    }
}