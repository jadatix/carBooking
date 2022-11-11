package org.jadatix.carbooking.service;

import org.jadatix.carbooking.model.IdentifierEntity;

import java.util.List;

interface EntityService<T extends IdentifierEntity> {
    List<T> getAll();

    T get(Long id);

    T create(T entity);

    void delete(Long id);

    T update(Long id, T newEntity);
}