package org.jadatix.carbooking.config;

import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuthWebSecurityConfiguration
{
    private JpaUserDetailsService userDetailsService;

    @Autowired
    public BasicAuthWebSecurityConfiguration(JpaUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/offices").hasAnyRole(Role.USER.toString(),Role.MANAGER.toString())
                .and()
                .authorizeRequests().antMatchers("/api/v1/**","/h2_console/**").hasRole(Role.MANAGER.toString())
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("email")
                .and()
                .logout()
                .and()
                .httpBasic()
                .and()
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
