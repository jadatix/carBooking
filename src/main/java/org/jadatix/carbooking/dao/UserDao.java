package org.jadatix.carbooking.dao;


import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;
import org.jadatix.carbooking.repository.UserRepository;
import org.springframework.data.jpa.domain.Specification;
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
    protected UserRepository getRepository() {
        return repository;
    }

    @Override
    protected Specification<User> getForReadOnly() {
        if (isManager()) {
            return null;
        }
        return getEqualSpecification("id", getCurrent().getId()).or(getEqualSpecification("role", Role.MANAGER));
    }

    @Override
    protected Specification<User> getForModify(Long id) {
        if (isManager()) {
            return null;
        }
        return getEqualSpecification("id", getCurrent().getId());
    }

}
