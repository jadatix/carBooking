package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.exception.AccessDeniedException;
import org.jadatix.carbooking.exception.NotFoundException;
import org.jadatix.carbooking.model.IdentifierEntity;
import org.jadatix.carbooking.repository.SpecificationRepository;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.jadatix.carbooking.service.AuthenticateUserService.isManager;

public interface DaoEntity<T extends IdentifierEntity> {
    default T get(Long id) {
        return getImmutable(id).orElse(null);
    }

    default List<T> getAll() {
        return getImmutableList();
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
        getRepository().delete(getMutable(id).orElseThrow(NotFoundException::new));
    }

    SpecificationRepository<T> getRepository();

    default Optional<T> getMutable(Long id) {
        if (isManager()) {
            return getRepository().findById(id);
        }
        throw new AccessDeniedException();
    }

    default Optional<T> getImmutable(Long id) {
        if (isManager()) {
            return getRepository().findById(id);
        }
        return Optional.empty();
    }

    default List<T> getImmutableList() {
        if (isManager()) {
            return getRepository().findAll();
        }
        return List.of();
    }

    default Specification<T> getSpecification(String fieldName, Object value, SpecificationOperator operator) {
        return (root, query, cb) -> {
            if (SpecificationOperator.EQUALS.equals(operator)) {
                return cb.equal(root.get(fieldName), value);
            }
            return null;
        };
    }
}