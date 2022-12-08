package org.jadatix.carbooking.config;

import org.jadatix.carbooking.service.JpaUserDetailsService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class BasicDummySecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService() {
        return mock(JpaUserDetailsService.class);
    }
}
