package org.jadatix.carbooking.api.v1.controller;

import org.jadatix.carbooking.api.v1.request.UserRequest;
import org.jadatix.carbooking.api.v1.response.UserResponse;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;
import org.jadatix.carbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;


@RestController
@RequestMapping("api/v1/users")
public class UserController extends AbstractController<User, UserRequest, UserResponse> {

    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    protected UserService getService() {
        return userService;
    }

    @Override
    protected UserResponse convertToResponse(User user) {
        return new UserResponse(user);
    }

    @Override
    protected void convertPatchField(User entity, Map.Entry<String, Object> requestBody) {
        String newValue = String.valueOf(requestBody.getValue());
        switch (requestBody.getKey()){
            case "role":
                Role role = Role.valueOf(newValue);
                entity.setRole(role);
                break;
            case "passport":
                entity.setPassport(newValue);
                break;
            case "fullName":
                entity.setFullName(newValue);
                break;
            case "email":
                entity.setEmail(newValue);
                break;
            case "secret":
                entity.setSecret(newValue);
                break;
            case "birthday":
                LocalDate date = LocalDate.parse(newValue);
                entity.setBirthday(date);
                break;
            default:
                break;
        }
    }
}
