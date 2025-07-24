package com.esb.esbapp.dto;



import java.time.LocalDate;
import java.util.Map;

public class CommunitySummaryDTO {
    private Integer monthAveragePoints;
    private Map<LocalDate, Integer> weeklyAveragePoints;

    public CommunitySummaryDTO() {
    }

    public CommunitySummaryDTO(Integer monthAveragePoints, Map<LocalDate, Integer> weeklyAveragePoints) {
        this.monthAveragePoints = monthAveragePoints;
        this.weeklyAveragePoints = weeklyAveragePoints;
    }

    public Integer getMonthAveragePoints() {
        return monthAveragePoints;
    }

    public void setMonthAveragePoints(Integer monthAveragePoints) {
        this.monthAveragePoints = monthAveragePoints;
    }

    public Map<LocalDate, Integer> getWeeklyAveragePoints() {
        return weeklyAveragePoints;
    }

    public void setWeeklyAveragePoints(Map<LocalDate, Integer> weeklyAveragePoints) {
        this.weeklyAveragePoints = weeklyAveragePoints;
    }
}
