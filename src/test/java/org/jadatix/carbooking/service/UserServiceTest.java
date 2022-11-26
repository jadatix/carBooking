package org.jadatix.carbooking.service;

import org.jadatix.carbooking.builder.UserBuilder;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest implements ServiceTest<User> {

    @Autowired
    private UserService service;

    @Override
    public void assertEntity(User actual, User expected) {
        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual.getRole(), expected.getRole());
        assertEquals(actual.getPassport(), expected.getPassport());
        assertEquals(actual.getFullName(), expected.getFullName());
        assertEquals(actual.getEmail(), expected.getEmail());
        assertEquals(actual.getBirthday(), expected.getBirthday());
    }

    @Test
    @Override
    public void testGetAsUserRole() {
        User user = pushToDB();
        loginAs(Role.USER);
        assertNull(getService().get(user.getId()));
    }

    @Test
    public void testGetByCurrentUser() {
        User user = pushToDB();
        loginUser(user);
        User expected = service.get(user.getId());
        assertEquals(user, expected);
    }

    @Test
    public void testGetManagerByUserRole() {
        User user = pushToDB(UserBuilder.builder().setRole(Role.MANAGER).build());
        loginAs(Role.USER);
        assertEquals(user, getService().get(user.getId()));
    }

    @Test
    public void testUpdateByCurrentUser() {
        User user = pushToDB();
        loginUser(user);
        User newUser = UserBuilder.builder().setId(user.getId()).build();
        assertDoesNotThrow(() -> service.update(newUser));
    }

    @Test
    void testDeleteByCurrentUser() {
        User user = pushToDB();

        loginUser(user);

        assertDoesNotThrow(() -> service.delete(user.getId()));
    }

    @Override
    public User generateEntity() {
        return UserBuilder.builder().build();
    }

    @Override
    public UserService getService() {
        return service;
    }

}
