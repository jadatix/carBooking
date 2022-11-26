package org.jadatix.carbooking.service;

import static java.util.Objects.isNull;

import org.jadatix.carbooking.exception.NotAuthenticatedException;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticateUserService {

    public static SecurityUser getCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isNull(authentication)) {
            throw new NotAuthenticatedException();
        }

        SecurityUser user = (SecurityUser) authentication.getPrincipal();

        if (isNull(user)) {
            throw new NotAuthenticatedException();
        }

        return user;
    }

    public static boolean isManager() {
        SecurityUser currentUser = getCurrent();
        return currentUser.getRole() == Role.MANAGER;
    }

    public static boolean isUser() {
        return !isManager();
    }

}