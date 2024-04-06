package com.carlos.person_management_app.exceptions.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

record ValidationExceptionDetail(String resource, String attribute, String justification) {
}

@Getter
abstract public class ValidationException extends BaseException implements Detailable<ValidationExceptionDetail> {

    protected static final String type = "ValidationError";
    protected ValidationExceptionDetail detail;

    protected ValidationException(
            String message,
            String resource,
            String attribute,
            String justification,
            Throwable cause
    ) {
        super(ValidationException.type,
                message,
                cause);

        this.detail = new ValidationExceptionDetail(resource, attribute, justification);
    }

    protected ValidationException(
            String message,
            String resource,
            String attribute,
            String justification
    ) {
        this(message, resource, attribute, justification, null);
    }

    public String getResource() {
        return detail.resource();
    }

    public String getAttribute() {
        return detail.attribute();
    }

    public String getJustification() {
        return detail.justification();
    }

    @Override
    protected Object toJsonObject() {
        Map<String, Object> details = new HashMap<>();
        String resource = getResource();
        String attribute = getAttribute();
        String justification = getJustification();

        if (resource != null) {
            details.put("resource", resource);
        }
        if (attribute != null) {
            details.put("attribute", attribute);
        }
        if (justification != null) {
            details.put("justification", justification);
        }
        Map<String, Object> combinedJson = new ObjectMapper().convertValue(super.toJsonObject(), Map.class);
        if (!details.isEmpty()) {
            combinedJson.put("details", details);
        }
        return combinedJson;
    }
}