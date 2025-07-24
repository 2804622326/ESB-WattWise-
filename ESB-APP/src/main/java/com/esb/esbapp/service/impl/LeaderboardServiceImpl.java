package com.esb.esbapp.service.impl;

import com.esb.esbapp.model.User;
import com.esb.esbapp.repository.UserRepository;
import com.esb.esbapp.service.LeaderboardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    private final UserRepository userRepository;

    public LeaderboardServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getDailyLeaderboard() {
        return userRepository.findTop10ByOrderByDailyPointsDesc();
    }

    @Override
    public List<User> getWeeklyLeaderboard() {
        return userRepository.findTop10ByOrderByWeeklyPointsDesc();
    }

    @Override
    public List<User> getOverallLeaderboard() {
        return userRepository.findTop10ByOrderByTotalPointsDesc();
    }
}
