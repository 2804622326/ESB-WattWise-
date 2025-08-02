package com.esb.esbapp.service;

import com.esb.esbapp.model.User;
import com.esb.esbapp.dto.UserSummaryDTO;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    List<User> getTop10ByDailyPoints(Long currentUserId);

    List<User> getTop10ByWeeklyPoints(Long currentUserId);

    List<User> getTop10ByTotalPoints(Long currentUserId);

    User completeTask(Long userId, Long taskId);

    String redeemReward(Long userId, Long rewardItemId);

    List<User> getAllUsers();

    UserSummaryDTO getUserSummary(Long userId);

    UserSummaryDTO getCommunitySummary();
}