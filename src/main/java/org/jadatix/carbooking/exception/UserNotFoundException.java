package org.jadatix.carbooking.exception;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("User doesn't exists.");
    }
}
