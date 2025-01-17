package com.zanguetsuinc.user_api.domain.repository;

import com.zanguetsuinc.user_api.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
