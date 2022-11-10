package org.jadatix.carbooking.service;

import org.jadatix.carbooking.exception.UserNotFoundException;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void getAll() {
        List<User> users = List.of(randomUser(), randomUser());
        users.forEach(userService::create);

        users.forEach(user -> {
            User actualUser = userService.get(user.getId());
            assertTrue(checkUser(user, actualUser));
        });
    }

    @Test
    void get() {
        User user = randomUser();
        userService.create(user);
        User actualUser = userService.get(user.getId());

        assertNotNull(actualUser.getId());
    }

    @Test
    void create() {
        User user = randomUser();
        userService.create(user);
        User actualUser = userService.get(user.getId());

        assertTrue(checkUser(user, actualUser));
    }

    @Test
    void delete() {
        User user = randomUser();
        userService.create(user);
        Long userId = user.getId();
        userService.delete(userId);
        assertThrows(UserNotFoundException.class, () -> userService.get(userId));
    }

    @Test
    void update() {
        User user = randomUser();
        userService.create(user);
        user.setRole(Role.MANAGER);
        User updatedUser = userService.update(user.getId(), user);

        User actualUser = userService.get(user.getId());

        assertTrue(checkUser(user, actualUser));
    }

    private String randomString()  {
        SecureRandom rand = new SecureRandom();
        return rand.ints(48, 123)
                .filter(Character::isAlphabetic)
                .limit(15)
                .mapToObj(c -> (char)c)
                .collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();
    }

    private User randomUser() {
        User user = new User();
        user.setRole(Role.USER);
        user.setPassport(randomString());
        user.setFullName(randomString());
        user.setEmail(randomString() + "@gmail.com");
        user.setSecret(randomString());
        user.setBirthday(LocalDate.of(2002, Month.SEPTEMBER, 10));
        return user;
    }

    private boolean checkUser(User user, User actualUser) {
        assertEquals(actualUser.getId(), user.getId());
        assertEquals(actualUser.getRole(), user.getRole());
        assertEquals(actualUser.getPassport(), user.getPassport());
        assertEquals(actualUser.getFullName(), user.getFullName());
        assertEquals(actualUser.getEmail(), user.getEmail());
        assertEquals(actualUser.getSecret(), user.getSecret());
        assertEquals(actualUser.getBirthday(), user.getBirthday());
        return true;
    }
}
