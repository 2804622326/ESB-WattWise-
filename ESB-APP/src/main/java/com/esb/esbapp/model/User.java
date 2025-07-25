package com.esb.esbapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String avatarUrl;

    private int dailyPoints;
    private int weeklyPoints;
    private int totalPoints;

    private double dailyEnergy;
    private double weeklyEnergy;
    private double monthlyEnergy;

    public User(Long id, String username, String avatarUrl,
                int dailyPoints, int weeklyPoints, int totalPoints,
                double dailyEnergy, double weeklyEnergy, double monthlyEnergy) {
        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.dailyPoints = dailyPoints;
        this.weeklyPoints = weeklyPoints;
        this.totalPoints = totalPoints;
        this.dailyEnergy = dailyEnergy;
        this.weeklyEnergy = weeklyEnergy;
        this.monthlyEnergy = monthlyEnergy;
    }
}
