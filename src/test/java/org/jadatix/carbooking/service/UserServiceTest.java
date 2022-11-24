package org.jadatix.carbooking.service;

import org.jadatix.carbooking.builder.UserBuilder;
import org.jadatix.carbooking.exception.AccessDeniedException;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
        assertThrows(AccessDeniedException.class, () -> service.update(user));
    }

    @Test
    void testUpdateUserByCurrentUser() {
        User currentUser = loginAs(Role.USER);
        currentUser.setId(1L);
        User user = UserBuilder.builder().setId(currentUser.getId()).build();
        assertDoesNotThrow(() -> service.update(user));
    }

    @Test
    void testDeleteUserByUserRole() {
        User currentUser = loginAs(Role.USER);
        currentUser.setId(1L);
        assertThrows(AccessDeniedException.class, () -> service.delete(currentUser.getId() + 1));
    }

    @Test
    void testDeleteUserByCurrentUser() {
        User currentUser = loginAs(Role.USER);
        assertDoesNotThrow(() -> service.delete(currentUser.getId()));
    }
}
