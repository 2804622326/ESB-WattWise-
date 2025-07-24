package com.esb.esbapp.service;

import com.esb.esbapp.model.User;

import java.util.List;

public interface LeaderboardService {
    List<User> getDailyLeaderboard();
    List<User> getWeeklyLeaderboard();
    List<User> getOverallLeaderboard();
}
