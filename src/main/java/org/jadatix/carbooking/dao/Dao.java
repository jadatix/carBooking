package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.model.IdentifierEntity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends IdentifierEntity> {
    Optional<T> get(Long id);

    List<T> getAll();

    T create(T entity);

    T update(T entity);

    void delete(Long id);
}