package com.esb.esbapp.service.impl;

import com.esb.esbapp.dto.EnergyStatsDTO;
import com.esb.esbapp.model.User;
import com.esb.esbapp.repository.UserRepository;
import com.esb.esbapp.service.StatsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {

    private final UserRepository userRepository;

    public StatsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public EnergyStatsDTO getHomeStats(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new EnergyStatsDTO(user.getMonthlyEnergy(), user.getTotalPoints());
    }

    @Override
    public EnergyStatsDTO getCommunityStats() {
        List<User> users = userRepository.findAll();
        double totalEnergy = users.stream().mapToDouble(User::getMonthlyEnergy).sum();
        int totalPoints = users.stream().mapToInt(User::getTotalPoints).sum();
        return new EnergyStatsDTO(totalEnergy, totalPoints);
    }
}
