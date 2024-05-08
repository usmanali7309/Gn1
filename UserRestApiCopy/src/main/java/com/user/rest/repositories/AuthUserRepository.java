package com.user.rest.repositories;

import com.user.rest.entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser,Long> {

    Optional<AuthUser> findByEmail(String email);
    Optional<AuthUser> findByUsernameOrEmail(String username, String email);
    Optional<AuthUser> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
