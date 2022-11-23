package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.Dao;
import org.jadatix.carbooking.exception.NotFoundException;
import org.jadatix.carbooking.model.IdentifierEntity;

import javax.transaction.Transactional;
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
    public T create(T t) {
        return getDao().create(t);
    }

    @Override
    public void delete(Long id) {
        Optional<T> found = getDao().get(id);
        if (found.isEmpty()) {
            throw new NotFoundException("Not found with id " + id);
        }
        getDao().delete(id);
    }

    @Override
    public T update(T t) {
        return getDao().update(t);
    }

    protected abstract Dao<T> getDao();

}