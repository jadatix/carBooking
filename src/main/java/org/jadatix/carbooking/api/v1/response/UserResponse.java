package org.jadatix.carbooking.api.v1.response;

import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;

import java.time.LocalDate;

public class UserResponse extends AbstractResponse {
    private Role role;
    private String passport;
    private String fullName;
    private String email;
    private String secret;
    private LocalDate birthday;

    public UserResponse(User user) {
        super(user.getId());
        this.role = user.getRole();
        this.passport = user.getPassport();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.secret = user.getSecret();
        this.birthday = user.getBirthday();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
