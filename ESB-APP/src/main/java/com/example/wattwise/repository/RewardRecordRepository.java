package com.example.wattwise.repository;

import com.example.wattwise.entity.RewardRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for RewardRecord entities.
 */
public interface RewardRecordRepository extends JpaRepository<RewardRecord, Long> {
}
