package org.jadatix.carbooking.api.v1.controller;

import org.jadatix.carbooking.api.v1.request.OfficeRequest;
import org.jadatix.carbooking.api.v1.request.UserRequest;
import org.jadatix.carbooking.api.v1.response.UserResponse;
import org.jadatix.carbooking.exception.FieldValidationException;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;
import org.jadatix.carbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("api/v1/users")
public class UserController extends AbstractController<User, UserRequest, UserResponse> {

    private UserService userService;
    private ConversionService converter;
    private Validator validator;

    @Autowired
    UserController(UserService userService,ConversionService converter){
        this.userService = userService;
        this.converter = converter;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
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
        switch (requestBody.getKey()){
            case "role":
                Role role = converter.convert(requestBody.getValue(),Role.class);
                validateField("role",role);
                entity.setRole(role);
                break;
            case "passport":
                String newPassport = converter.convert(requestBody.getValue(),String.class);
                validateField("passport",newPassport);
                entity.setPassport(newPassport);
                break;
            case "fullName":
                String newFullName = converter.convert(requestBody.getValue(),String.class);
                validateField("fullName",newFullName);
                entity.setFullName(newFullName);
                break;
            case "email":
                String newEmail = converter.convert(requestBody.getValue(),String.class);
                validateField("email",newEmail);
                entity.setEmail(newEmail);
                break;
            case "secret":
                String newSecret = converter.convert(requestBody.getValue(),String.class);
                validateField("secret",newSecret);
                entity.setSecret(newSecret);
                break;
            case "birthday":
                LocalDate date = LocalDate.parse(requestBody.getValue().toString());
                validateField("birthday",date);
                entity.setBirthday(date);
                break;
            default:
                break;
        }
    }

    @Override
    protected void validateField(String fieldName, Object fieldValue) {
        Set<ConstraintViolation<UserRequest>> result = validator.validateValue(UserRequest.class,fieldName,fieldValue);
        if(!result.isEmpty()){
            throw new FieldValidationException(result.toString());
        }
    }
}
