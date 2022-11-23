package org.jadatix.carbooking.service;

import org.jadatix.carbooking.model.IdentifierEntity;

import java.util.List;

interface EntityService<T extends IdentifierEntity> {

    List<T> getAll();

    T get(Long id);

    T create(T t);

    void delete(Long id);

    T update(T t);

}