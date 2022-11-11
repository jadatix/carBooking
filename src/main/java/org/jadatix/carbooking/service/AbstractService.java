package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.Dao;
import org.jadatix.carbooking.model.IdentifierEntity;

import java.util.List;
import java.util.Optional;

abstract class AbstractService<T extends IdentifierEntity> implements EntityService<T> {
    @Override
    public List<T> getAll() {
        return getDao().getAll();
    }

    @Override
    public T get(Long id) {
        return getDao().get(id).orElse(null);
    }

    @Override
    public T create(T entity) {
       return getDao().create(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<T> found = getDao().get(id);
        if (found.isEmpty()) {
            throw new RuntimeException();
        }
        getDao().delete(id);
    }

    @Override
    public T update(Long id, T newEntity) {
        newEntity.setId(id);
        return getDao().update(newEntity);
    }

    protected abstract Dao<T> getDao();
}