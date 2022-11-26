package org.jadatix.carbooking.repository;

import org.jadatix.carbooking.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends SpecificationRepository<User> {
    Optional<User> findByEmail(@Param("email") String email);
}
