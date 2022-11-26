package org.jadatix.carbooking.service;

import org.jadatix.carbooking.dao.UserDao;
import org.jadatix.carbooking.exception.NotFoundException;
import org.jadatix.carbooking.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class for user auth
 *
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {
    private UserDao userDao;

    @Autowired
    public JpaUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Here email is used instead of Username
     * 
     * @param email an email
     * @return user cast to SecurityUser which implements UserDetails interface
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao
                .findByEmail(email)
                .map(SecurityUser::new)
                .orElseThrow(NotFoundException::new);
    }
}
