package com.example.wattwise.repository;

import com.example.wattwise.entity.LeaderboardEntry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for LeaderboardEntry entities.
 */
public interface LeaderboardRepository extends JpaRepository<LeaderboardEntry, Long> {
}
