package com.example.wattwise.repository;

import com.example.wattwise.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Task entities.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
