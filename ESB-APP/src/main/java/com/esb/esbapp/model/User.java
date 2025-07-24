package com.esb.esbapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
