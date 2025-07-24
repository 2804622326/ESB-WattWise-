package com.esb.esbapp.repository;

import com.esb.esbapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Task entities.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
