package org.jadatix.carbooking.exception;

public class NotAuthenticatedException extends RuntimeException {
    public NotAuthenticatedException() {
        super("User not authenticated");
    }
}