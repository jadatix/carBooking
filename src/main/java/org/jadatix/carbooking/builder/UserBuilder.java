package org.jadatix.carbooking.builder;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Objects;

import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;

public class UserBuilder {

    private User user;

    public UserBuilder() {
        init();
    }

    private void init() {
        Integer value = new SecureRandom().nextInt(1, 99999);
        user = new User();
        setEmail("Email_" + value + "@gmail.com");
        setFullName("FullName_" + value);
        setPassport("Passport_" + value);
        setSecret("Secret_" + value);
        setRole(Role.USER);
        setBirthday(LocalDate.of(1991, value % 12, value % 28));
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public User build() {
        User oldUser = this.user;
        init();
        return oldUser;
    }

    public UserBuilder setId(Long id) {
        user.setId(id);
        return this;
    }

    public UserBuilder setRole(Role role) {
        user.setRole(role);
        return this;
    }

    public UserBuilder setPassport(String passport) {
        user.setPassport(passport);
        return this;
    }

    public UserBuilder setFullName(String fullName) {
        user.setFullName(fullName);
        return this;
    }

    public UserBuilder setEmail(String email) {
        user.setEmail(email);
        return this;
    }

    public UserBuilder setSecret(String secret) {
        user.setSecret(secret);
        return this;
    }

    public UserBuilder setBirthday(LocalDate birthday) {
        user.setBirthday(birthday);
        return this;
    }
}