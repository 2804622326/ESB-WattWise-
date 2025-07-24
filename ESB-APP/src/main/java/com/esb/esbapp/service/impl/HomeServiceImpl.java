package com.esb.esbapp.service.impl;

import com.esb.esbapp.dto.CommunitySummaryDTO;
import com.esb.esbapp.dto.UserSummaryDTO;
import com.esb.esbapp.model.User;
import com.esb.esbapp.repository.UserRepository;
import com.esb.esbapp.service.HomeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    private final UserRepository userRepository;

    public HomeServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserSummaryDTO getUserSummary(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
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
        if (users.isEmpty()) {
            return new CommunitySummaryDTO(0,0,0,0,0,0);
        }

        int dailyPointsSum = 0;
        int weeklyPointsSum = 0;
        int totalPointsSum = 0;
        double dailyEnergySum = 0;
        double weeklyEnergySum = 0;
        double monthlyEnergySum = 0;

        for (User u : users) {
            dailyPointsSum += u.getDailyPoints();
            weeklyPointsSum += u.getWeeklyPoints();
            totalPointsSum += u.getTotalPoints();
            dailyEnergySum += u.getDailyEnergy();
            weeklyEnergySum += u.getWeeklyEnergy();
            monthlyEnergySum += u.getMonthlyEnergy();
        }

        int count = users.size();
        return new CommunitySummaryDTO(
                dailyPointsSum / count,
                weeklyPointsSum / count,
                totalPointsSum / count,
                dailyEnergySum / count,
                weeklyEnergySum / count,
                monthlyEnergySum / count
        );
    }
}
