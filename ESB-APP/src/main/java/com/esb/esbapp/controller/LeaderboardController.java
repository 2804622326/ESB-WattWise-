package com.esb.esbapp.controller;

import com.esb.esbapp.model.LeaderboardEntry;
import com.esb.esbapp.service.LeaderboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/daily/{communityId}")
    public List<LeaderboardEntry> daily(@PathVariable String communityId) {
        return leaderboardService.getDailyLeaderboard(communityId);
    }

    @GetMapping("/weekly/{communityId}")
    public List<LeaderboardEntry> weekly(@PathVariable String communityId) {
        return leaderboardService.getWeeklyLeaderboard(communityId);
    }

    @GetMapping("/overall/{communityId}")
    public List<LeaderboardEntry> overall(@PathVariable String communityId) {
        return leaderboardService.getOverallLeaderboard(communityId);
    }
}
