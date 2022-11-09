package org.jadatix.carbooking.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User doesn't exists.");
    }
}
