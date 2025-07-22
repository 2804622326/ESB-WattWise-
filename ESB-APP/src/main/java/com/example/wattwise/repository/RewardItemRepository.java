package com.example.wattwise.repository;

import com.example.wattwise.entity.RewardItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for RewardItem entities.
 */
public interface RewardItemRepository extends JpaRepository<RewardItem, Long> {
}
