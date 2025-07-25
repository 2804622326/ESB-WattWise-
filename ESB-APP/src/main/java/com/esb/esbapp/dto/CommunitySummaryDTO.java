package com.esb.esbapp.dto;




public class CommunitySummaryDTO {
    private int averageDailyPoints;
    private int averageWeeklyPoints;
    private int averageTotalPoints;
    private double averageDailyEnergy;
    private double averageWeeklyEnergy;
    private double averageMonthlyEnergy;

    public CommunitySummaryDTO() {
    }

    public CommunitySummaryDTO(int averageDailyPoints, int averageWeeklyPoints,
                               int averageTotalPoints, double averageDailyEnergy,
                               double averageWeeklyEnergy, double averageMonthlyEnergy) {
        this.averageDailyPoints = averageDailyPoints;
        this.averageWeeklyPoints = averageWeeklyPoints;
        this.averageTotalPoints = averageTotalPoints;
        this.averageDailyEnergy = averageDailyEnergy;
        this.averageWeeklyEnergy = averageWeeklyEnergy;
        this.averageMonthlyEnergy = averageMonthlyEnergy;
    }

    public int getAverageDailyPoints() {
        return averageDailyPoints;
    }

    public void setAverageDailyPoints(int averageDailyPoints) {
        this.averageDailyPoints = averageDailyPoints;
    }

    public int getAverageWeeklyPoints() {
        return averageWeeklyPoints;
    }

    public void setAverageWeeklyPoints(int averageWeeklyPoints) {
        this.averageWeeklyPoints = averageWeeklyPoints;
    }

    public int getAverageTotalPoints() {
        return averageTotalPoints;
    }

    public void setAverageTotalPoints(int averageTotalPoints) {
        this.averageTotalPoints = averageTotalPoints;
    }

    public double getAverageDailyEnergy() {
        return averageDailyEnergy;
    }

    public void setAverageDailyEnergy(double averageDailyEnergy) {
        this.averageDailyEnergy = averageDailyEnergy;
    }

    public double getAverageWeeklyEnergy() {
        return averageWeeklyEnergy;
    }

    public void setAverageWeeklyEnergy(double averageWeeklyEnergy) {
        this.averageWeeklyEnergy = averageWeeklyEnergy;
    }

    public double getAverageMonthlyEnergy() {
        return averageMonthlyEnergy;
    }

    public void setAverageMonthlyEnergy(double averageMonthlyEnergy) {
        this.averageMonthlyEnergy = averageMonthlyEnergy;
    }
}
