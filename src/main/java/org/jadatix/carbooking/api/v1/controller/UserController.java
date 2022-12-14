package org.jadatix.carbooking.api.v1.controller;

import org.jadatix.carbooking.api.v1.request.UserRequest;
import org.jadatix.carbooking.api.v1.response.UserResponse;
import org.jadatix.carbooking.model.User;
import org.jadatix.carbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/users")
public class UserController extends AbstractController<User, UserRequest, UserResponse> {
    @Autowired
    private UserService userService;

    protected UserService getService() {
        return userService;
    }

    @Override
    protected UserResponse convertToResponse(User user) {
        return new UserResponse(user);
    }
}
