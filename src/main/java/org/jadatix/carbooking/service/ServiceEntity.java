package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.DaoEntity;
import org.jadatix.carbooking.model.IdentifierEntity;

import java.util.List;

public interface ServiceEntity<T extends IdentifierEntity> {
    default List<T> getAll() {
        return getDao().getAll();
    }

    default T get(Long id) {
        return getDao().get(id);
    }

    default T create(T entity) {
        return getDao().create(entity);
    }

    default void delete(Long id) {
        getDao().delete(id);
    }

    default T update(T entity) {
        return getDao().update(entity);
    }

    DaoEntity<T> getDao();
}