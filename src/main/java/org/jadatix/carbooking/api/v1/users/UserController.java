package org.jadatix.carbooking.api.v1.users;

import org.jadatix.carbooking.api.v1.ControllerEntity;
import org.jadatix.carbooking.model.User;
import org.jadatix.carbooking.service.ServiceEntity;
import org.jadatix.carbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
