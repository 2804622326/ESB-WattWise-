package com.esb.esbapp.dto;




public class CommunitySummaryDTO {
    private int dailyPoints;
    private int weeklyPoints;
    private int totalPoints;
    private double dailyEnergy;
    private double weeklyEnergy;
    private double monthlyEnergy;

    public CommunitySummaryDTO() {
    }

    public CommunitySummaryDTO(int dailyPoints, int weeklyPoints,
                               int totalPoints, double dailyEnergy,
                               double weeklyEnergy, double monthlyEnergy) {
        this.dailyPoints = dailyPoints;
        this.weeklyPoints = weeklyPoints;
        this.totalPoints = totalPoints;
        this.dailyEnergy = dailyEnergy;
        this.weeklyEnergy = weeklyEnergy;
        this.monthlyEnergy = monthlyEnergy;
    }

    public int getDailyPoints() {
        return dailyPoints;
    }

    public void setDailyPoints(int dailyPoints) {
        this.dailyPoints = dailyPoints;
    }

    public int getWeeklyPoints() {
        return weeklyPoints;
    }

    public void setWeeklyPoints(int weeklyPoints) {
        this.weeklyPoints = weeklyPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public double getDailyEnergy() {
        return dailyEnergy;
    }

    public void setDailyEnergy(double dailyEnergy) {
        this.dailyEnergy = dailyEnergy;
    }

    public double getWeeklyEnergy() {
        return weeklyEnergy;
    }

    public void setWeeklyEnergy(double weeklyEnergy) {
        this.weeklyEnergy = weeklyEnergy;
    }

    public double getMonthlyEnergy() {
        return monthlyEnergy;
    }

    public void setMonthlyEnergy(double monthlyEnergy) {
        this.monthlyEnergy = monthlyEnergy;
    }
}
