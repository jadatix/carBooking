package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.exception.AccessDeniedException;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.SecurityUser;
import org.jadatix.carbooking.model.User;
import org.jadatix.carbooking.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.jadatix.carbooking.service.AuthenticateUserService.getCurrent;
import static org.jadatix.carbooking.service.AuthenticateUserService.isManager;

@Component
public class UserDao extends AbstractDao<User> {

    private final UserRepository repository;

    public UserDao(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findByEmail(String email) {
        return getRepository().findByEmail(email);
    }

    @Override
    public Optional<User> get(Long id) {
        if (isAllowed(id)) {
            return getRepository().findById(id);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        if (isManager()) {
            return getRepository().findAll();
        }
        return null;
    }

    @Override
    public User update(User user) {
        if (isAllowed(user.getId())) {
            return super.update(user);
        }
        throw new AccessDeniedException();
    }

    @Override
    public void delete(Long id) {
        if (isAllowed(id)) {
           super.delete(id);
        } else {
            throw new AccessDeniedException();
        }
    }

    @Override
    protected UserRepository getRepository() {
        return repository;
    }

    private boolean isAllowed(Long id) {
        SecurityUser currentUser = getCurrent();
        return id.equals(currentUser.getId()) || Role.MANAGER.equals(currentUser.getRole());
    }

}
