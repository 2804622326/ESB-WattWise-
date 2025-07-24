package com.esb.esbapp.service;

import com.esb.esbapp.model.RewardItem;

import java.util.List;

public interface RewardService {
    List<RewardItem> getAllRewards();

    /**
     * Attempt to redeem the specified reward item for a user.
     *
     * @return true if redemption succeeded
     */
    boolean redeem(Long userId, Long rewardItemId);
}
