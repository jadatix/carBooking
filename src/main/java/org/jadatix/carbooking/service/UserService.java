package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.UserDao;
import org.jadatix.carbooking.exception.UserAlreadyExistsException;
import org.jadatix.carbooking.exception.UserNotFoundException;
import org.jadatix.carbooking.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {
    private UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public User create(User user) {
        try {
            return super.create(user);
        } catch (RuntimeException ex) {
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            super.delete(id);
        } catch (RuntimeException ex) {
           throw new UserNotFoundException();
        }
    }

    @Override
    protected UserDao getDao() {
       return dao;
    }
}
