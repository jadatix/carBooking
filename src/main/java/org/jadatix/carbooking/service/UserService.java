package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.UserDao;
import org.jadatix.carbooking.exception.UserAlreadyExistsException;
import org.jadatix.carbooking.model.User;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {

    private UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public User create(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setSecret(encoder.encode(user.getSecret()));
        try {
            return super.create(user);
        } catch (DataIntegrityViolationException exception){
            throw new UserAlreadyExistsException();
        }

    }

    @Override
    protected UserDao getDao() {
        return dao;
    }

}
