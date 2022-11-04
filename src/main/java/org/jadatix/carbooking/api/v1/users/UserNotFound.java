package org.jadatix.carbooking.api.v1.users;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User doesn't exists.");
    }
}
