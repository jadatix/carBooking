package org.jadatix.carbooking.demodata;

import org.jadatix.carbooking.builder.UserBuilder;
import org.jadatix.carbooking.dao.OfficeDao;
import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Configuration
public class OfficeDemoData {

    private static final Logger log = LoggerFactory.getLogger(OfficeDemoData.class);
    @Bean
    CommandLineRunner initDatabase(OfficeDao officeDao) {
        SecurityContextHolder.clearContext();
        var user = UserBuilder.builder().build();
        user.setRole(Role.MANAGER);
        SecurityUser securityUser = new SecurityUser(user);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(securityUser,
                securityUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(token);

        return args -> {

            List<Office> offices = Arrays.asList(new Office("Chernivtsi", "Holovna"),
                    new Office("Chernivtsi", "S. Bandery"));
            offices.forEach(office -> {
                log.info("Preloading {}", office);
                officeDao.create(office);
            });
        };
    }

}
