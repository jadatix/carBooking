package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.model.IdentifierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
abstract class AbstractDao<T extends IdentifierEntity> implements Dao<T> {

    @Override
    public Optional<T> get(Long id) {
        return getRepository().findById(id);
    }

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }

    @Override
    public T create(T t) {
        return getRepository().save(t);
    }

    @Override
    public T update(T t) {
        return getRepository().save(t);
    }

    @Override
    public void delete(Long id) {
        Optional<T> found = get(id);
        found.ifPresent(getRepository()::delete);
    }

    protected abstract JpaRepository<T, Long> getRepository();

}
