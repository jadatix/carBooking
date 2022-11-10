package org.jadatix.carbooking.service;

import org.jadatix.carbooking.model.IdentifierEntity;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


abstract class AbstractServiceTest<T extends IdentifierEntity> {
    @Test
    void getAll() {
        List<T> entities = List.of(generateEntity(), generateEntity());
        entities.forEach(getService()::create);
        entities.forEach(t -> assertEntity(t, getService().get(t.getId())));
    }

    @Test
    void get() {
        T entity = generateEntity();
        getService().create(entity);
        T actual = getService().get(entity.getId());

        assertNotNull(actual.getId());
    }

    @Test
    void create() {
        T entity = generateEntity();
        getService().create(entity);
        T actual = getService().get(entity.getId());

        assertEntity(entity, actual);
    }

   @Test
   void delete() {
        T entity = generateEntity();
        getService().create(entity);
        Long id = entity.getId();
        getService().delete(id);

        assertNull(getService().get(id));
   }

   @Test
   void deleteNotExists() {
        T entity = generateEntity();
        getService().delete(entity.getId());
   }


    @Test
    void update() {
        T entity = generateEntity();
        getService().create(entity);

        T newEntity = generateEntity();
        newEntity.setId(entity.getId());

        getService().update(entity.getId(), newEntity);
        T actual = getService().get(entity.getId());

        assertEntity(entity, actual);
    }

    protected String getRandomString() {
        SecureRandom rand = new SecureRandom();
        return rand.ints(48, 123)
                .filter(Character::isAlphabetic)
                .limit(15)
                .mapToObj(c -> (char) c)
                .collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();
    }

    protected abstract void assertEntity(T actualEntity, T expectedEntity);

    protected abstract T generateEntity();

    protected abstract EntityService<T> getService();
}
