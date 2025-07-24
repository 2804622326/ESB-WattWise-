package com.esb.esbapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunitySummaryDTO {
    private int averageDailyPoints;
    private int averageWeeklyPoints;
    private int averageTotalPoints;
    private double averageDailyEnergy;
    private double averageWeeklyEnergy;
    private double averageMonthlyEnergy;
}
