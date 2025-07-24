package com.esb.esbapp.service;

import com.esb.esbapp.model.User;

import java.util.List;

public interface LeaderboardService {
    List<User> getDailyLeaderboard(Long userId);
    List<User> getWeeklyLeaderboard(Long userId);
    List<User> getOverallLeaderboard(Long userId);
}
