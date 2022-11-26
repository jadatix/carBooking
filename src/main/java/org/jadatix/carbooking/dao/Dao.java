package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.exception.AccessDeniedException;
import org.jadatix.carbooking.exception.NotFoundException;
import org.jadatix.carbooking.model.IdentifierEntity;
import org.jadatix.carbooking.service.AuthenticateUserService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static org.jadatix.carbooking.service.AuthenticateUserService.isManager;

public interface Dao<T extends IdentifierEntity> {

    default T get(Long id) {
        return getRepository().findById(id).orElse(null);
    }


    default List<T> getAll() {
        return getRepository().findAll();
    }


    default T create(T entity) {
        if (isManager()) {
            return getRepository().save(entity);
        }
        throw new AccessDeniedException();
    }

    default T update(T entity) {
        if (isManager()) {
            return getRepository().save(entity);
        }
        throw new AccessDeniedException();
    }


    default void delete(Long id) {
        if (isManager()) {
            Optional<T> found = Optional.ofNullable(get(id));
            getRepository().delete(found.orElseThrow(NotFoundException::new));
        } else {
            throw new AccessDeniedException();
        }
    }

    JpaRepository<T, Long> getRepository();

}