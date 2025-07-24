package com.esb.esbapp.repository;

import com.esb.esbapp.model.TaskRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for TaskRecord entities.
 */
public interface TaskRecordRepository extends JpaRepository<TaskRecord, Long> {
}
