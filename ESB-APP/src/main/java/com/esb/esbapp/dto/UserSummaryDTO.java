package com.esb.esbapp.dto;



import java.time.LocalDate;
import java.util.Map;

public class UserSummaryDTO {
    private Integer monthPoints;
    private Map<LocalDate, Integer> weeklyPoints;

    public UserSummaryDTO() {
    }

    public UserSummaryDTO(Integer monthPoints, Map<LocalDate, Integer> weeklyPoints) {
        this.monthPoints = monthPoints;
        this.weeklyPoints = weeklyPoints;
    }

    public Integer getMonthPoints() {
        return monthPoints;
    }

    public void setMonthPoints(Integer monthPoints) {
        this.monthPoints = monthPoints;
    }

    public Map<LocalDate, Integer> getWeeklyPoints() {
        return weeklyPoints;
    }

    public void setWeeklyPoints(Map<LocalDate, Integer> weeklyPoints) {
        this.weeklyPoints = weeklyPoints;
    }
}
