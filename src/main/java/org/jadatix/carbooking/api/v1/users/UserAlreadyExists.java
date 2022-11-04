package org.jadatix.carbooking.api.v1.users;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User already exists with");
    }
}
