package com.esb.esbapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDTO {
    private int dailyPoints;
    private int weeklyPoints;
    private int totalPoints;
    private double dailyEnergy;
    private double weeklyEnergy;
    private double monthlyEnergy;
}
