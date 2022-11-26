package org.jadatix.carbooking.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Entity not found");
    }
}
