package org.jadatix.carbooking.demodata;

import org.jadatix.carbooking.dao.UserDao;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserDemoData {
    @Bean
    CommandLineRunner commandLineRunner(UserDao userDao) {
        return args -> {
            User user1 = User.builder().setRole(Role.USER).setFullName("Ivan Tkachuk").setBirthday(LocalDate.of(2002, Month.SEPTEMBER, 10)).build();
            User user2 = User.builder().setRole(Role.MANAGER).setFullName("Andrii Liashenko").setBirthday(LocalDate.of(2002, Month.DECEMBER, 8)).build();
            List.of(user1, user2).forEach(userDao::create);
        };
    }
}
