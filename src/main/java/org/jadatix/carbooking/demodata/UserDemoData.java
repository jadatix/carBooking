package org.jadatix.carbooking.demodata;

import org.jadatix.carbooking.dao.UserDao;
import org.jadatix.carbooking.models.Role;
import org.jadatix.carbooking.models.User;
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
            User ivan = new User(
                    Role.USER,
                    "passport1",
                    "Ivan Tkachuk",
                    "tkachuk.ivan.v@chnu.edu.ua",
                    "secretpassword",
                    LocalDate.of(2002, Month.SEPTEMBER, 10)
            );

            User andrii = new User(
                    Role.USER,
                    "passport2",
                    "Andrii Liashenko",
                    "andrii.liashenko@chnu.edu.ua",
                    "secretpassword",
                    LocalDate.of(2002, Month.DECEMBER, 8)
            );

            List.of(ivan, andrii).forEach(userDao::save);
        };
    }
}
