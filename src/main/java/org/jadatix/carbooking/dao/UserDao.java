package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.exception.AccessDeniedException;
import org.jadatix.carbooking.model.SecurityUser;
import org.jadatix.carbooking.model.User;
import org.jadatix.carbooking.repository.UserRepository;
import org.springframework.stereotype.Component;

import static org.jadatix.carbooking.service.AuthenticateUserService.getCurrent;
import static org.jadatix.carbooking.service.AuthenticateUserService.isManager;

import java.util.List;
import java.util.Optional;

@Component
public class UserDao extends AbstractDao<User> {

    private UserRepository repository;

    public UserDao(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findByEmail(String email) {
        return getRepository().findByEmail(email);
    }

    // @Override
    // public Optional<User> get(Long id) {
    // SecurityUser currentUser = getCurrent();
    // if (currentUser.getId() == id) {
    // return getRepository().findById(id);
    // }
    // throw new AccessDeniedException();
    // }

    @Override
    public List<User> getAll() {
        if (isManager()) {
            return getRepository().findAll();
        }
        throw new AccessDeniedException();
    }

    @Override
    public User update(User user) {
        SecurityUser currentUser = getCurrent();
        if (currentUser.getId() == user.getId() || isManager()) {
            return super.update(user);
        }
        throw new AccessDeniedException();
    }

    @Override
    protected UserRepository getRepository() {
        return repository;
    }
}
