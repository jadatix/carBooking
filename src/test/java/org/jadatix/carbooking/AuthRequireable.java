package org.jadatix.carbooking;

import org.jadatix.carbooking.builder.UserBuilder;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.SecurityUser;
import org.jadatix.carbooking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AuthRequireable {

    @BeforeEach
    public void logoutUser() {
        loginAs(Role.MANAGER);
    }

    protected User loginAs(Role role) {
        User user = UserBuilder.builder().setRole(role).build();
        loginUser(user);
        return user;
    }

    protected void loginUser(User user) {
        SecurityContextHolder.clearContext();
        SecurityUser securityUser = new SecurityUser(user);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(securityUser,
                securityUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
