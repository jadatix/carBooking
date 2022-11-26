package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.model.User;
import org.jadatix.carbooking.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDao implements Dao<User> {

    private UserRepository repository;

    public UserDao(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findByEmail(String email) {
        return getRepository().findByEmail(email);
    }

    @Override
    public UserRepository getRepository() {
        return repository;
    }

}
