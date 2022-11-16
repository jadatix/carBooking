package org.jadatix.carbooking.service;

import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(actual.getSecret(), expected.getSecret());
        assertEquals(actual.getBirthday(), expected.getBirthday());
    }

    @Override
    protected User generateEntity() {
        User user = new User();
        user.setRole(Role.USER);
        user.setPassport(getRandomString());
        user.setFullName(getRandomString());
        user.setEmail(getRandomString() + "@gmail.com");
        user.setSecret(getRandomString());
        user.setBirthday(LocalDate.of(2002, Month.SEPTEMBER, 10));
        return user;
    }

    @Override
    protected UserService getService() {
        return service;
    }

}
