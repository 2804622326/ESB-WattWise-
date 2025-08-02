package com.esb.esbapp.service;

import com.esb.esbapp.dto.EnergyStatsDTO;

public interface StatsService {
    EnergyStatsDTO getHomeStats(Long userId);
    EnergyStatsDTO getCommunityStats();
}
