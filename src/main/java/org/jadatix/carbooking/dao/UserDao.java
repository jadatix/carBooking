package org.jadatix.carbooking.dao;

import org.jadatix.carbooking.models.User;
import org.jadatix.carbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDao {
    private final UserRepository userRepository;

    @Autowired
    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User update(Long id, User user) {
        return userRepository.findById(id)
                .map(user1 -> {
                    user1.setRole(user.getRole());
                    return userRepository.save(user1);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                });
    }

    public void delete(Long id) {
        Optional<User> foundUser = get(id);
        foundUser.ifPresent(userRepository::delete);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
