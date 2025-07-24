package com.esb.esbapp.controller;

import com.esb.esbapp.model.User;
import com.esb.esbapp.service.LeaderboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/daily")
    public List<User> daily(@RequestParam(required = false) Long userId) {
        return leaderboardService.getDailyLeaderboard(userId);
    }

    @GetMapping("/weekly")
    public List<User> weekly(@RequestParam(required = false) Long userId) {
        return leaderboardService.getWeeklyLeaderboard(userId);
    }

    @GetMapping("/all")
    public List<User> overall(@RequestParam(required = false) Long userId) {
        return leaderboardService.getOverallLeaderboard(userId);
    }
}
