package com.esb.esbapp.service.impl;

import com.esb.esbapp.model.LeaderboardEntry;
import com.esb.esbapp.model.TaskRecord;
import com.esb.esbapp.model.User;
import com.esb.esbapp.repository.TaskRecordRepository;
import com.esb.esbapp.repository.UserRepository;
import com.esb.esbapp.service.LeaderboardService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    private final UserRepository userRepository;
    private final TaskRecordRepository taskRecordRepository;

    public LeaderboardServiceImpl(UserRepository userRepository, TaskRecordRepository taskRecordRepository) {
        this.userRepository = userRepository;
        this.taskRecordRepository = taskRecordRepository;
    }

    @Override
    public List<LeaderboardEntry> getDailyLeaderboard(String communityId) {
        LocalDate today = LocalDate.now();
        return buildLeaderboard(communityId, today, today);
    }

    @Override
    public List<LeaderboardEntry> getWeeklyLeaderboard(String communityId) {
        LocalDate now = LocalDate.now();
        LocalDate start = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return buildLeaderboard(communityId, start, now);
    }

    @Override
    public List<LeaderboardEntry> getOverallLeaderboard(String communityId) {
        List<User> users = userRepository.findTop10ByCommunityIdOrderByTotalPointsDesc(communityId);
        return users.stream()
                .map(u -> LeaderboardEntry.builder()
                        .userName(u.getName())
                        .score(u.getTotalPoints())
                        .communityId(u.getCommunityId())
                        .build())
                .collect(Collectors.toList());
    }

    private List<LeaderboardEntry> buildLeaderboard(String communityId, LocalDate start, LocalDate end) {
        List<User> users = userRepository.findByCommunityId(communityId);
        Map<Long, Integer> scores = new HashMap<>();
        for (User user : users) {
            List<TaskRecord> records = taskRecordRepository.findByUserIdAndCompletedDateBetween(user.getId(), start, end);
            int total = records.stream().mapToInt(TaskRecord::getEarnedPoints).sum();
            if (total > 0) {
                scores.put(user.getId(), total);
            }
        }
        return scores.entrySet().stream()
                .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                .limit(10)
                .map(e -> {
                    User u = userRepository.findById(e.getKey()).orElseThrow();
                    return LeaderboardEntry.builder()
                            .userName(u.getName())
                            .score(e.getValue())
                            .communityId(u.getCommunityId())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
