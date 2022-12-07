package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.AbstractDao;
import org.jadatix.carbooking.model.IdentifierEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class AbstractService<T extends IdentifierEntity> {

    public List<T> getAll() {
        return getDao().getAll();
    }

    public T get(Long id) {
        return getDao().get(id).orElse(null);
    }

    public Page<T> get(Pageable pageable) {
        return getDao().get(pageable);
    }

    public T create(T t) {
        return getDao().create(t);
    }

    public void delete(Long id) {
        getDao().delete(id);
    }

    public T update(T t) {
        return getDao().update(t);
    }

    protected abstract AbstractDao<T> getDao();

}