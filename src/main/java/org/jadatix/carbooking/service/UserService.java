package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.UserDao;
import org.jadatix.carbooking.exception.UserAlreadyExistsException;
import org.jadatix.carbooking.model.User;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserService extends AbstractService<User> {

    private UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public User create(User user) {
        return performUpdateOrCreate(user, UserService.super::create);
    }

    @Override
    public User update(User user) {
        return performUpdateOrCreate(user, UserService.super::update);
    }

    private User performUpdateOrCreate(User user, Function<User,User> function){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setSecret(encoder.encode(user.getSecret()));
        try {
            return function.apply(user);
        } catch (DataIntegrityViolationException exception){
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    protected UserDao getDao() {
        return dao;
    }

}
