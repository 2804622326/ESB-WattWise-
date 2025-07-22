package com.example.wattwise.repository;

import com.example.wattwise.entity.TaskRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for TaskRecord entities.
 */
public interface TaskRecordRepository extends JpaRepository<TaskRecord, Long> {
}
