package com.esb.esbapp.service.impl;

import com.esb.esbapp.model.RewardItem;
import com.esb.esbapp.repository.RewardItemRepository;
import com.esb.esbapp.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final RewardItemRepository rewardItemRepository;

    @Override
    public List<RewardItem> getAllRewards() {
        return rewardItemRepository.findAll();
    }

    @Override
    public RewardItem getRewardById(Long id) {
        return rewardItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reward not found"));
    }
}