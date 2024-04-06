package com.carlos.person_management_app.exceptions;

import com.carlos.person_management_app.exceptions.common.ResourceException;

public class CannotDeleteException extends ResourceException {
    private static final String MESSAGE = "Recurso não pode ser deletado";

    private CannotDeleteException(String resource, String attribute) {
        super(
                CannotDeleteException.MESSAGE,
                resource,
                attribute,
                null
        );
    }

    private CannotDeleteException(String resource, String attribute, String justification) {
        super(
                CannotDeleteException.MESSAGE,
                resource,
                attribute,
                justification
        );
    }

    public static CannotDeleteException create(String resource) {
        return CannotDeleteException.create(resource, null);
    }

    public static CannotDeleteException create(String resource, String attribute) {
        return new CannotDeleteException(resource, attribute);
    }

    public static CannotDeleteException create(String resource, String attribute, String justification) {
        return new CannotDeleteException(resource, attribute, justification);
    }
}
