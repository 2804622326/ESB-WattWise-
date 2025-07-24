package com.esb.esbapp.service.impl;

import com.esb.esbapp.dto.CommunitySummaryDTO;
import com.esb.esbapp.dto.UserSummaryDTO;
import com.esb.esbapp.model.TaskRecord;
import com.esb.esbapp.model.User;
import com.esb.esbapp.repository.TaskRecordRepository;
import com.esb.esbapp.repository.UserRepository;
import com.esb.esbapp.service.HomeService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {

    private final TaskRecordRepository taskRecordRepository;
    private final UserRepository userRepository;

    public HomeServiceImpl(TaskRecordRepository taskRecordRepository, UserRepository userRepository) {
        this.taskRecordRepository = taskRecordRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserSummaryDTO getUserSummary(Long userId) {
        LocalDate now = LocalDate.now();
        LocalDate monthStart = now.withDayOfMonth(1);
        LocalDate weekStart = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        List<TaskRecord> monthRecords = taskRecordRepository.findByUserIdAndCompletedDateBetween(userId, monthStart, now);
        int monthPoints = monthRecords.stream().mapToInt(TaskRecord::getEarnedPoints).sum();

        Map<LocalDate, Integer> weekly = new HashMap<>();
        LocalDate day = weekStart;
        while (!day.isAfter(now)) {
            int points = taskRecordRepository.findByUserIdAndCompletedDate(userId, day)
                    .stream()
                    .mapToInt(TaskRecord::getEarnedPoints)
                    .sum();
            weekly.put(day, points);
            day = day.plusDays(1);
        }

        return new UserSummaryDTO(monthPoints, weekly);
    }

    @Override
    public CommunitySummaryDTO getCommunitySummary(String communityId) {
        LocalDate now = LocalDate.now();
        LocalDate monthStart = now.withDayOfMonth(1);
        LocalDate weekStart = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        List<User> users = userRepository.findByCommunityId(communityId);
        if (users.isEmpty()) {
            return new CommunitySummaryDTO(0, Map.of());
        }

        int monthTotal = 0;
        Map<LocalDate, Integer> weeklyTotal = new HashMap<>();
        for (User user : users) {
            List<TaskRecord> monthRecords = taskRecordRepository.findByUserIdAndCompletedDateBetween(user.getId(), monthStart, now);
            monthTotal += monthRecords.stream().mapToInt(TaskRecord::getEarnedPoints).sum();
        }
        for (LocalDate d = weekStart; !d.isAfter(now); d = d.plusDays(1)) {
            int dayTotal = 0;
            for (User user : users) {
                dayTotal += taskRecordRepository.findByUserIdAndCompletedDate(user.getId(), d)
                        .stream()
                        .mapToInt(TaskRecord::getEarnedPoints)
                        .sum();
            }
            weeklyTotal.put(d, dayTotal / users.size());
        }

        int monthAvg = monthTotal / users.size();
        return new CommunitySummaryDTO(monthAvg, weeklyTotal);
    }
}
