package org.jadatix.carbooking.api.v1.request;

import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;

import java.time.LocalDate;

public class UserRequest extends AbstractRequest<User> {

    private Long id;
    private Role role;
    private String passport;
    private String fullName;
    private String email;
    private String secret;
    private LocalDate birthday;

    public UserRequest() {

    }

    public UserRequest(User user) {
        this.id = user.getId();
        this.role = user.getRole();
        this.passport = user.getPassport();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.secret = user.getSecret();
        this.birthday = user.getBirthday();
    }

    @Override
    public User create() {
        User user = new User();
        user.setId(id);
        user.setRole(role);
        user.setPassport(passport);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setSecret(secret);
        user.setBirthday(birthday);
        return user;
    }

    @Override
    public User update(User user) {
        user.setRole(role);
        user.setPassport(passport);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setSecret(secret);
        user.setBirthday(birthday);
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
