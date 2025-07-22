package com.esb.esbapp.repository;

import com.esb.esbapp.model.LeaderboardEntry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for LeaderboardEntry entities.
 */
public interface LeaderboardRepository extends JpaRepository<LeaderboardEntry, Long> {
}
