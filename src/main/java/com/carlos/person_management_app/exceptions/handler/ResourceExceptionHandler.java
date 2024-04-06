package com.carlos.person_management_app.exceptions.handler;

import com.carlos.person_management_app.exceptions.CannotDeleteException;
import com.carlos.person_management_app.exceptions.common.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<?> resourceException(ResourceException exception) {
        return new ResponseEntity<>(exception.toJson(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(CannotDeleteException.class)
    public ResponseEntity<?> resourceException(CannotDeleteException exception) {
        return new ResponseEntity<>(exception.toJson(), HttpStatus.FORBIDDEN);
    }
}