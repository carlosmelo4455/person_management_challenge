package com.carlos.person_management_app.exceptions.handler;

import com.carlos.person_management_app.exceptions.ParseDataException;
import com.carlos.person_management_app.exceptions.common.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> resourceException(ValidationException exception) {
        return new ResponseEntity<>(exception.toJson(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ParseDataException.class)
    public ResponseEntity<?> resourceException(ParseDataException exception) {
        return new ResponseEntity<>(exception.toJson(), HttpStatus.BAD_REQUEST);
    }

}
