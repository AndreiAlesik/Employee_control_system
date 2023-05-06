package com.example.demowithtests.repository;

import com.example.demowithtests.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername (String username);
}
