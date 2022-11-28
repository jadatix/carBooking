package org.jadatix.carbooking.service;

import org.jadatix.carbooking.builder.UserBuilder;
import org.jadatix.carbooking.exception.AccessDeniedException;
import org.jadatix.carbooking.exception.NotFoundException;
import org.jadatix.carbooking.model.IdentifierEntity;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.SecurityUser;
import org.jadatix.carbooking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

interface ServiceTest<T extends IdentifierEntity> {

    @BeforeEach
    default void logoutUser() {
        loginAs(Role.MANAGER);
    }

    default User loginAs(Role role) {
        User user = UserBuilder.builder().setRole(role).build();
        loginUser(user);
        return user;
    }

    default void loginUser(User user) {
        SecurityContextHolder.clearContext();
        SecurityUser securityUser = new SecurityUser(user);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(securityUser,
                securityUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    @Test
    default void getAll() {
        List<T> entities = List.of(generateEntity(), generateEntity());
        entities.forEach(getService()::create);
        entities.forEach(entity -> assertEntity(entity, getService().get(entity.getId())));
    }

    @Test
    default void get() {
        T entity = pushToDB();
        T actual = getService().get(entity.getId());
        assertNotNull(actual.getId());
    }

    @Test
    default void create() {
        T entity = pushToDB();
        T actual = getService().get(entity.getId());
        assertEntity(entity, actual);
    }

    @Test
    default void delete() {
        T entity = pushToDB();

        Long id = entity.getId();
        getService().delete(id);

        assertNull(getService().get(id));
    }

    @Test
    default void deleteNotExists() {
        assertThrows(NotFoundException.class, () -> getService().delete(0L));
    }

    @Test
    default void update() {
        T entity = pushToDB();

        T newEntity = generateEntity();
        newEntity.setId(entity.getId());

        getService().update(newEntity);

        T actual = getService().get(entity.getId());
        assertEntity(newEntity, actual);
    }

    @Test
    default void testCreateAsUserRole() {
        loginAs(Role.USER);
        T entity = generateEntity();
        assertThrows(AccessDeniedException.class, () -> getService().create(entity));
    }

    @Test
    default void testGetAsUserRole() {
        T entity = pushToDB();
        loginAs(Role.USER);

        assertEquals(entity, getService().get(entity.getId()));
    }

    @Test
    default void testUpdateAsUserRole() {
        T entity = pushToDB();
        loginAs(Role.USER);

        T newEntity = generateEntity();
        newEntity.setId(entity.getId());

        assertThrows(AccessDeniedException.class, () -> getService().update(newEntity));
    }

    @Test
    default void testDeleteAsUserRole() {
        T entity = pushToDB();
        loginAs(Role.USER);
        assertThrows(AccessDeniedException.class, () -> getService().delete(entity.getId()));
    }

    default T pushToDB() {
        T entity = generateEntity();
        getService().create(entity);
        return entity;
    }

    default T pushToDB(T entity) {
        getService().create(entity);
        return entity;
    }

    void assertEntity(T actual, T expected);

    T generateEntity();

    ServiceEntity<T> getService();

}
