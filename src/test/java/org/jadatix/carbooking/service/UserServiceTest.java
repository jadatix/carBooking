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
    void testCreateUserByUserRole() {
        loginAs(Role.USER);
        User user = UserBuilder.builder().build();
        assertThrows(AccessDeniedException.class, () -> service.create(user));
    }

    @Test
    void testCreteUserByManagerRole() {
        User user = UserBuilder.builder().build();
        assertDoesNotThrow(() -> service.create(user));
    }

    @Test
    void testUpdateUserByUserRole() {
        User user = loginAs(Role.USER);
        user.setId(1L);
        User newUser = UserBuilder.builder().setId(user.getId() + 1).build();
        assertThrows(AccessDeniedException.class, () -> service.update(newUser));
    }

    @Test
    void testUpdateUserByCurrentUser() {
        User user = loginAs(Role.USER);
        user.setId(1L);
        User newUser = UserBuilder.builder().setId(user.getId()).build();
        assertDoesNotThrow(() -> service.update(newUser));
    }

    @Test
    void testUpdateUserByManagerRole() {
        User user = UserBuilder.builder().build();
        service.create(user);

        User newUser = UserBuilder.builder().build();
        newUser.setId(user.getId());
        assertDoesNotThrow(() -> service.update(newUser));
    }
}
