package com.esb.esbapp.repository;

import com.esb.esbapp.model.TaskRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository for TaskRecord entities.
 */
public interface TaskRecordRepository extends JpaRepository<TaskRecord, Long> {

    List<TaskRecord> findByUserIdAndCompletedDate(Long userId, LocalDate date);

    List<TaskRecord> findByUserIdAndCompletedDateBetween(Long userId, LocalDate start, LocalDate end);

    List<TaskRecord> findByCompletedDate(LocalDate date);

    List<TaskRecord> findByCompletedDateBetween(LocalDate start, LocalDate end);
}
