package com.carlos.person_management_app.exceptions;

import com.carlos.person_management_app.exceptions.common.ResourceException;

public class NotFoundException extends ResourceException {
    private static final String MESSAGE = "Recurso não encontrado";

    private NotFoundException(String resource, String attribute) {
        super(
                NotFoundException.MESSAGE,
                resource,
                attribute,
                null
        );
    }

    private NotFoundException(String resource, String attribute, String justification) {
        super(
                NotFoundException.MESSAGE,
                resource,
                attribute,
                justification
        );
    }

    public static NotFoundException create(String resource) {
        return NotFoundException.create(resource, null);
    }

    public static NotFoundException create(String resource, String attribute) {
        return new NotFoundException(resource, attribute);
    }

    public static NotFoundException create(String resource, String attribute, String justification) {
        return new NotFoundException(resource, attribute, justification);
    }
}
