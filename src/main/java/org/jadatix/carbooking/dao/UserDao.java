package org.jadatix.carbooking.dao;

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
public class UserDao implements DaoEntity<User> {
    private UserRepository repository;

    public UserDao(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findByEmail(String email) {
        return getRepository().findByEmail(email);
    }

    @Override
    public User update(User user) {
        SecurityUser currentUser = getCurrent();
        if (user.getId().equals(currentUser.getId())) {
            return getRepository().save(user);
        }
        return DaoEntity.super.update(user);
    }

    @Override
    public UserRepository getRepository() {
        return repository;
    }

    @Override
    public Optional<User> getMutable(Long id) {
        SecurityUser currentUser = getCurrent();
        if (id.equals(currentUser.getId())) {
            return getRepository().findOne(getSpecification("id", currentUser.getId(), SpecificationOperator.EQUALS));
        }
        return DaoEntity.super.getMutable(id);
    }

    @Override
    public Optional<User> getImmutable(Long id) {
        if (!isManager()) {
            return getRepository()
                    .findOne(getSpecification("id", getCurrent().getId(), SpecificationOperator.EQUALS)
                            .or(getSpecification("role", Role.MANAGER, SpecificationOperator.EQUALS)
                                    .and(getSpecification("id", id, SpecificationOperator.EQUALS))));
        }
        return DaoEntity.super.getImmutable(id);
    }

    @Override
    public List<User> getImmutableList() {
        if (!isManager()) {
            return getRepository().findAll(getSpecification("id", getCurrent().getId(), SpecificationOperator.EQUALS)
                    .or(getSpecification("role", Role.MANAGER, SpecificationOperator.EQUALS)));
        }
        return DaoEntity.super.getImmutableList();
    }
}
