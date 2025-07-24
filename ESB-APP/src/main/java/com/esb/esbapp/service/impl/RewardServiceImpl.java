package com.esb.esbapp.service.impl;

import com.esb.esbapp.model.RewardItem;
import com.esb.esbapp.model.User;
import com.esb.esbapp.repository.RewardItemRepository;
import com.esb.esbapp.repository.UserRepository;
import com.esb.esbapp.service.RewardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {

    private final RewardItemRepository rewardItemRepository;
    private final UserRepository userRepository;

    public RewardServiceImpl(RewardItemRepository rewardItemRepository, UserRepository userRepository) {
        this.rewardItemRepository = rewardItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<RewardItem> getAllRewards() {
        return rewardItemRepository.findAll();
    }

    @Override
    @Transactional
    public boolean redeem(Long userId, Long rewardItemId) {
        RewardItem item = rewardItemRepository.findById(rewardItemId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (item == null || user == null || user.getTotalPoints() < item.getCostPoints()) {
            return false;
        }
        user.setTotalPoints(user.getTotalPoints() - item.getCostPoints());
        userRepository.save(user);
        return true;
    }
}
