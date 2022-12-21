package org.jadatix.carbooking.api;

import org.jadatix.carbooking.exception.FieldValidationException;
import org.jadatix.carbooking.exception.UserAlreadyExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import org.jadatix.carbooking.exception.NotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String notFoundExceptionHandler(NotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    String userAlreadyExistsExceptionHandler(UserAlreadyExistsException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(FieldValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    String fieldValidationExceptionHandler(FieldValidationException exception){return exception.getMessage();}
}
