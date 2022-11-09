package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.UserDao;
import org.jadatix.carbooking.api.v1.users.UserAlreadyExists;
import org.jadatix.carbooking.api.v1.users.UserNotFound;
import org.jadatix.carbooking.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public User getUser(Long id) {
        return userDao.get(id).orElseThrow(UserNotFound::new);
    }

    public void createUser(User user) {
        Optional<User> foundUser = userDao.findByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            throw new UserAlreadyExists();
        }
        userDao.save(user);
    }

    public void deleteUserById(Long id) {
        Optional<User> existingUser = userDao.get(id);
        if (existingUser.isEmpty()) {
            throw new UserNotFound();
        }
        userDao.delete(id);
    }

    public User updateUserById(Long id, User user) {
        return userDao.update(id, user);
    }
}
