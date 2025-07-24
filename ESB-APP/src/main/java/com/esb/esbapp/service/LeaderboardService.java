package com.esb.esbapp.service;

import com.esb.esbapp.model.LeaderboardEntry;

import java.util.List;

public interface LeaderboardService {
    List<LeaderboardEntry> getDailyLeaderboard(String communityId);
    List<LeaderboardEntry> getWeeklyLeaderboard(String communityId);
    List<LeaderboardEntry> getOverallLeaderboard(String communityId);
}
