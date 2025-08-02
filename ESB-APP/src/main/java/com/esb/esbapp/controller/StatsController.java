package com.esb.esbapp.controller;

import com.esb.esbapp.dto.EnergyStatsDTO;
import com.esb.esbapp.service.StatsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "*")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/home/{userId}")
    public EnergyStatsDTO getHomeStats(@PathVariable Long userId) {
        return statsService.getHomeStats(userId);
    }

    @GetMapping("/community")
    public EnergyStatsDTO getCommunityStats() {
        return statsService.getCommunityStats();
    }
}
