package org.jadatix.carbooking.api.v1.controller;

import org.jadatix.carbooking.api.v1.request.UserRequest;
import org.jadatix.carbooking.api.v1.response.UserResponse;
import org.jadatix.carbooking.model.User;
import org.jadatix.carbooking.service.AbstractService;
import org.jadatix.carbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController extends AbstractController<User, UserRequest, UserResponse>{

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @Override
    protected AbstractService<User> getService() {
        return service;
    }

    @Override
    protected UserResponse toResponse(User user) {
        UserResponse ur = new UserResponse(user.getId());
        ur.setRole(user.getRole());
        ur.setBirthday(user.getBirthday());
        ur.setFullName(user.getFullName());
        ur.setEmail(user.getEmail());
        return ur;
    }
}
