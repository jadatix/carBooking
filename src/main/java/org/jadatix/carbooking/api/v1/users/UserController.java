package org.jadatix.carbooking.api.v1.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.jadatix.carbooking.api.v1.ControllerEntity;
import org.jadatix.carbooking.model.User;
import org.jadatix.carbooking.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController implements ControllerEntity<User> {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @Override
    public UserService getService() {
        return service;
    }
}
