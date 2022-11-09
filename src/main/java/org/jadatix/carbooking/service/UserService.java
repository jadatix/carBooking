package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.UserDao;
import org.jadatix.carbooking.exception.UserAlreadyExistsException;
import org.jadatix.carbooking.exception.UserNotFoundException;
import org.jadatix.carbooking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User get(Long id) {
        return userDao.get(id).orElseThrow(UserNotFoundException::new);
    }

    public User create(User user) {
        Optional<User> foundUser = userDao.findByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        return userDao.create(user);
    }

    public void delete(Long id) {
        Optional<User> existingUser = userDao.get(id);
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        userDao.delete(id);
    }

    public User update(Long id, User user) {
        user.setId(id);
        return userDao.update(user);
    }
}
