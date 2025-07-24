package com.esb.esbapp.controller;

import com.esb.esbapp.model.RewardItem;
import com.esb.esbapp.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    @GetMapping
    public List<RewardItem> getAllRewards() {
        return rewardService.getAllRewards();
    }
}