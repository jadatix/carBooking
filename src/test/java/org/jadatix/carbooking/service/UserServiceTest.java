package org.jadatix.carbooking.service;

import org.hibernate.annotations.NotFound;
import org.jadatix.carbooking.builder.UserBuilder;
import org.jadatix.carbooking.exception.AccessDeniedException;
import org.jadatix.carbooking.exception.NotFoundException;
import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest extends AbstractServiceTest<User> {

    @Autowired
    private UserService service;

    @Override
    protected void assertEntity(User actual, User expected) {
        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual.getRole(), expected.getRole());
        assertEquals(actual.getPassport(), expected.getPassport());
        assertEquals(actual.getFullName(), expected.getFullName());
        assertEquals(actual.getEmail(), expected.getEmail());
        assertEquals(actual.getBirthday(), expected.getBirthday());
    }

    @Override
    protected User generateEntity() {
        return UserBuilder.builder().build();
    }

    @Override
    protected UserService getService() {
        return service;
    }

    @Test
    void testGetUserByUserRole() {
        User user = loginAs(Role.USER);
        assertNull(service.get(0L));
    }

    @Test
    void testGetUserByCurrentUser() {
        User user = UserBuilder.builder().build();
        User createdUser = service.create(user);

        loginUser(createdUser);

        Long id = createdUser.getId();
        User expectedUser = service.get(id);

        assertEquals(id, expectedUser.getId());
    }

    @Test
    void testGetManagerByUserRole() {
        User user = UserBuilder.builder().setRole(Role.MANAGER).build();
        service.create(user);

        loginAs(Role.USER);

        assertEquals(user, service.get(user.getId()));
    }


    @Test
    void testGetAllUserByUserRole() {
        User user = UserBuilder.builder().build();
        service.create(user);

        loginAs(Role.USER);

        assertFalse(service.getAll().contains(user));
    }

    @Test
    void testGetAllManagerByUserRole() {
        User user = UserBuilder.builder().setRole(Role.MANAGER).build();
        service.create(user);

        loginAs(Role.USER);

        assertTrue(service.getAll().contains(user));
    }

    @Test
    void testCreateUserByUserRole() {
        loginAs(Role.USER);
        User user = UserBuilder.builder().build();
        assertThrows(AccessDeniedException.class, () -> service.create(user));
    }

    @Test
    void testUpdateUserByUserRole() {
        User currentUser = loginAs(Role.USER);
        currentUser.setId(1L);
        User user = UserBuilder.builder().setId(currentUser.getId() + 1).build();
        assertThrows(NotFoundException.class, () -> service.update(user));
    }

    @Test
    void testUpdateUserByCurrentUser() {
        User currentUser = pushEntityToDb();
        loginUser(currentUser);
        User user = UserBuilder.builder().setId(currentUser.getId()).build();
        assertDoesNotThrow(() -> service.update(user));
    }

    @Test
    void testDeleteUserByUserRole() {
        User user = UserBuilder.builder().build();
        User createdUser = service.create(user);

        loginAs(Role.USER);

        assertThrows(NotFoundException.class, () -> service.delete(createdUser.getId()));
    }

    @Test
    void testDeleteUserByCurrentUser() {
        User user = UserBuilder.builder().build();
        User createdUser = service.create(user);

        loginUser(createdUser);

        assertDoesNotThrow(() -> service.delete(createdUser.getId()));
    }
}
