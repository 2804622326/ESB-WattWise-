package com.example.wattwise.repository;

import com.example.wattwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
