package org.jadatix.carbooking.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("Access Denied");
    }
}