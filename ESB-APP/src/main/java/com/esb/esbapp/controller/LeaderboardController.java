package com.esb.esbapp.controller;

import com.esb.esbapp.model.User;
import com.esb.esbapp.service.LeaderboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<User> daily() {
        return leaderboardService.getDailyLeaderboard();
    }

    @GetMapping("/weekly")
    public List<User> weekly() {
        return leaderboardService.getWeeklyLeaderboard();
    }

    @GetMapping("/all")
    public List<User> overall() {
        return leaderboardService.getOverallLeaderboard();
    }
}
