package org.jadatix.carbooking.service;

import org.jadatix.carbooking.exception.NotFoundException;
import org.jadatix.carbooking.model.IdentifierEntity;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


abstract class AbstractServiceTest<T extends IdentifierEntity> {
    private EntityService<T> service;

    public AbstractServiceTest() {
        this.service = getService();
    }

    @Test
    void getAll() {
        List<T> entities = List.of(generateEntity(), generateEntity());
        entities.forEach(service::create);
        entities.forEach(t -> assertEntity(t, service.get(t.getId())));
    }

    @Test
    void get() {
        T entity = generateEntity();
        service.create(entity);
        T actual = service.get(entity.getId());

        assertNotNull(actual.getId());
    }

    @Test
    void create() {
        T entity = generateEntity();
        service.create(entity);
        T actual = service.get(entity.getId());

        assertEntity(entity, actual);
    }

   @Test
   void delete() {
        T entity = generateEntity();
        service.create(entity);
        Long id = entity.getId();
        service.delete(id);

        assertNull(service.get(id));
   }

   @Test
   void deleteNotExists() {
        T entity = generateEntity();
        Long id = entity.getId();
        EntityService<T> service = getService();
        assertThrows(NotFoundException.class, () -> service.delete(id));
   }


    @Test
    void update() {
        T entity = generateEntity();
        service.create(entity);

        Long id = entity.getId();

        T newEntity = generateEntity();
        newEntity.setId(id);

        service.update(id, newEntity);
        T actual = getService().get(id);

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
