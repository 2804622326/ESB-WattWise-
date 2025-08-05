package com.esb.esbapp.service.impl;

import com.esb.esbapp.dto.CommunitySummaryDTO;
import com.esb.esbapp.dto.UserSummaryDTO;
import com.esb.esbapp.model.RewardItem;
import com.esb.esbapp.model.Task;
import com.esb.esbapp.model.User;
import com.esb.esbapp.repository.UserRepository;
import com.esb.esbapp.repository.TaskRepository;
import com.esb.esbapp.repository.RewardItemRepository;
import com.esb.esbapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final RewardItemRepository rewardItemRepository;

    public UserServiceImpl(UserRepository userRepository,
                           TaskRepository taskRepository,
                           RewardItemRepository rewardItemRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.rewardItemRepository = rewardItemRepository;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getTop10ByDailyPoints(Long currentUserId) {
        return getTop10WithPinnedUser(userRepository.findTop10ByOrderByDailyPointsDesc(), currentUserId,
                Comparator.comparingInt(User::getDailyPoints).reversed());
    }

    @Override
    public List<User> getTop10ByWeeklyPoints(Long currentUserId) {
        return getTop10WithPinnedUser(userRepository.findTop10ByOrderByWeeklyPointsDesc(), currentUserId,
                Comparator.comparingInt(User::getWeeklyPoints).reversed());
    }

    @Override
    public List<User> getTop10ByTotalPoints(Long currentUserId) {
        return getTop10WithPinnedUser(userRepository.findTop10ByOrderByTotalPointsDesc(), currentUserId,
                Comparator.comparingInt(User::getTotalPoints).reversed());
    }

    private List<User> getTop10WithPinnedUser(List<User> top10, Long currentUserId, Comparator<User> comparator) {
        Set<Long> ids = top10.stream().map(User::getId).collect(Collectors.toSet());
        if (!ids.contains(currentUserId)) {
            userRepository.findById(currentUserId).ifPresent(user -> top10.add(0, user));
        }
        return top10.stream().distinct().sorted(comparator).limit(11).collect(Collectors.toList());
    }

    @Override
    public User completeTask(Long userId, Long taskId) {
        User user = getUserById(userId);
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        int reward = task.getRewardPoints();
        user.setDailyPoints(user.getDailyPoints() + reward);
        user.setWeeklyPoints(user.getWeeklyPoints() + reward);
        user.setTotalPoints(user.getTotalPoints() + reward);

        return userRepository.save(user);
    }

    @Override
    public String redeemReward(Long userId, Long rewardItemId) {
        User user = getUserById(userId);
        RewardItem reward = rewardItemRepository.findById(rewardItemId)
                .orElseThrow(() -> new RuntimeException("Reward item not found"));

        if (user.getTotalPoints() >= reward.getCostPoints()) {
            user.setTotalPoints(user.getTotalPoints() - reward.getCostPoints());
            userRepository.save(user);
            return "Reward redeemed successfully!";
        } else {
            return "Not enough points.";
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserSummaryDTO getUserSummary(Long id) {
        User user = getUserById(id);
        return new UserSummaryDTO(
                user.getDailyPoints(),
                user.getWeeklyPoints(),
                user.getTotalPoints(),
                user.getDailyEnergy(),
                user.getWeeklyEnergy(),
                user.getMonthlyEnergy()
        );
    }

    @Override
    public CommunitySummaryDTO getCommunitySummary() {
        List<User> users = userRepository.findAll();
        int count = users.size();
        if (count == 0) {
            return new CommunitySummaryDTO();
        }

        int sumDailyPoints = users.stream().mapToInt(User::getDailyPoints).sum();
        int sumWeeklyPoints = users.stream().mapToInt(User::getWeeklyPoints).sum();
        int sumTotalPoints = users.stream().mapToInt(User::getTotalPoints).sum();

        double sumDailyEnergy = users.stream().mapToDouble(User::getDailyEnergy).sum();
        double sumWeeklyEnergy = users.stream().mapToDouble(User::getWeeklyEnergy).sum();
        double sumMonthlyEnergy = users.stream().mapToDouble(User::getMonthlyEnergy).sum();

        return new CommunitySummaryDTO(
                sumDailyPoints / count,
                sumWeeklyPoints / count,
                sumTotalPoints / count,
                sumDailyEnergy / count,
                sumWeeklyEnergy / count,
                sumMonthlyEnergy / count
        );
    }
}