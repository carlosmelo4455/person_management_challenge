package com.carlos.person_management_app.exceptions;

import com.carlos.person_management_app.exceptions.common.ValidationException;

public class ParseDataException extends ValidationException {
    private static final String MESSAGE = "Data enviada incorretamente. DD/MM/YYYY.";

    private ParseDataException(String resource, String attribute) {
        super(
                ParseDataException.MESSAGE,
                resource,
                attribute,
                null
        );
    }

    private ParseDataException(String resource, String attribute, String justification) {
        super(
                ParseDataException.MESSAGE,
                resource,
                attribute,
                justification
        );
    }

    public static ParseDataException create(String resource) {
        return ParseDataException.create(resource, null);
    }

    public static ParseDataException create(String resource, String attribute) {
        return new ParseDataException(resource, attribute);
    }

    public static ParseDataException create(String resource, String attribute, String justification) {
        return new ParseDataException(resource, attribute, justification);
    }
}
