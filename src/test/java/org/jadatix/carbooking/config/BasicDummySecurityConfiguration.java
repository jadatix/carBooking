package org.jadatix.carbooking.config;

import org.jadatix.carbooking.service.OfficeService;
import org.jadatix.carbooking.service.UserDetailsServiceImpl;
import org.jadatix.carbooking.service.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class BasicDummySecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService() {
        return mock(UserDetailsServiceImpl.class);
    }

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }

    @Bean
    public OfficeService officeService() {
        return mock(OfficeService.class);
    }
}
