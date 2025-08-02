package com.esb.esbapp.model;

import jakarta.persistence.*;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private int dailyPoints;
    private int weeklyPoints;
    private int totalPoints;

    private double dailyEnergy;
    private double weeklyEnergy;
    private double monthlyEnergy;

    private String imageUrl;

    public User() {
    }

    public User(Long id, String username,
                int dailyPoints, int weeklyPoints, int totalPoints,
                double dailyEnergy, double weeklyEnergy, double monthlyEnergy,
                String imageUrl) {
        this.id = id;
        this.username = username;
        this.dailyPoints = dailyPoints;
        this.weeklyPoints = weeklyPoints;
        this.totalPoints = totalPoints;
        this.dailyEnergy = dailyEnergy;
        this.weeklyEnergy = weeklyEnergy;
        this.monthlyEnergy = monthlyEnergy;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
