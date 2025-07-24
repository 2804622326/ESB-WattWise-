package com.esb.esbapp.service;

import com.esb.esbapp.model.RewardItem;

import java.util.List;

public interface RewardService {
    List<RewardItem> getAllRewards();
    void redeem(Long userId, Long rewardItemId);
}
