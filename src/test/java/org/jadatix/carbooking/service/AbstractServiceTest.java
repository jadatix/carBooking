package org.jadatix.carbooking.service;

import org.jadatix.carbooking.AuthRequireable;
import org.jadatix.carbooking.builder.UserBuilder;
import org.jadatix.carbooking.exception.NotFoundException;
import org.jadatix.carbooking.model.IdentifierEntity;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.SecurityUser;
import org.jadatix.carbooking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractServiceTest<T extends IdentifierEntity> extends AuthRequireable {

    protected T pushEntityToDb(){
        T t = generateEntity();
        getService().create(t);
        return t;
    }

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
        assertThrows(NotFoundException.class, () -> getService().delete(0L));
    }

    @Test
    void update() {
        T entity = generateEntity();
        getService().create(entity);

        Long id = entity.getId();

        T newEntity = generateEntity();
        newEntity.setId(id);

        getService().update(newEntity);
        T actual = getService().get(id);

        assertEntity(newEntity, actual);
    }

    protected abstract void assertEntity(T actualEntity, T expectedEntity);

    protected abstract T generateEntity();

    protected abstract AbstractService<T> getService();

}
