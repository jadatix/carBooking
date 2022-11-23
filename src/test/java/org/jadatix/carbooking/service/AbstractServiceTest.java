package org.jadatix.carbooking.service;

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

abstract class AbstractServiceTest<T extends IdentifierEntity> {

    private EntityService<T> service;

    public AbstractServiceTest() {
        this.service = getService();
    }

    @BeforeEach
    public void logoutUser() {
        loginAs(Role.MANAGER);
    }

    protected User loginAs(Role role) {
        User user = UserBuilder.builder().setRole(role).build();
        loginUser(user);
        return user;
    }

    protected void loginUser(User user) {
        SecurityContextHolder.clearContext();
        SecurityUser securityUser = new SecurityUser(user);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(securityUser,
                securityUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(token);
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

    protected abstract void assertEntity(T actualEntity, T expectedEntity);

    protected abstract T generateEntity();

    protected abstract EntityService<T> getService();

}
