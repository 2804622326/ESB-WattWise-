package com.esb.esbapp.controller;

import com.esb.esbapp.model.RewardItem;
import com.esb.esbapp.service.RewardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping
    public List<RewardItem> getRewards() {
        return rewardService.getAllRewards();
    }
}
