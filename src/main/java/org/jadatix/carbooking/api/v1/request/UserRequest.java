package org.jadatix.carbooking.api.v1.request;

import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;

import java.time.LocalDate;

public class UserRequest extends AbstractRequest<User> {

    private Role role;
    private String fullName;
    private String secret;
    private String passport;
    private String email;
    private LocalDate birthday;

    @Override
    public User create() {
        User user = new User();
        return update(user);
    }

    @Override
    public User update(User user) {
        user.setRole(user.getRole());
        user.setPassport(user.getPassport());
        user.setSecret(user.getSecret());
        user.setBirthday(user.getBirthday());
        user.setFullName(user.getFullName());
        user.setEmail(user.getEmail());
        return user;
    }
}
