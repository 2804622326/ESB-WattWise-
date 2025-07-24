package com.esb.esbapp.service.impl;

import com.esb.esbapp.model.User;
import com.esb.esbapp.repository.UserRepository;
import com.esb.esbapp.service.LeaderboardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    private final UserRepository userRepository;

    public LeaderboardServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getDailyLeaderboard(Long userId) {
        List<User> list = userRepository.findTop10ByOrderByDailyPointsDesc();
        return prependUser(list, userId);
    }

    @Override
    public List<User> getWeeklyLeaderboard(Long userId) {
        List<User> list = userRepository.findTop10ByOrderByWeeklyPointsDesc();
        return prependUser(list, userId);
    }

    @Override
    public List<User> getOverallLeaderboard(Long userId) {
        List<User> list = userRepository.findTop10ByOrderByTotalPointsDesc();
        return prependUser(list, userId);
    }

    private List<User> prependUser(List<User> list, Long userId) {
        if (userId == null) {
            return list;
        }
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            list.removeIf(u -> u.getId().equals(userId));
            list.add(0, user);
        }
        return list;
    }
}
