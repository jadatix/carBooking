package org.jadatix.carbooking.api;

import org.jadatix.carbooking.api.v1.users.UserAlreadyExists;
import org.jadatix.carbooking.api.v1.users.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFound ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userAlreadyExistHandler(UserAlreadyExists ex) {
        return ex.getMessage();
    }
}
