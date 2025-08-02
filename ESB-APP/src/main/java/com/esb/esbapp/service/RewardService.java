package com.esb.esbapp.service;

import com.esb.esbapp.model.RewardItem;
import java.util.List;

public interface RewardService {
    List<RewardItem> getAllRewards();
    RewardItem getRewardById(Long id); // 给兑换奖品逻辑使用
}