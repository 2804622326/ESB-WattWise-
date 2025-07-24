package com.esb.esbapp.model;

import jakarta.persistence.*;
import java.util.List;

import com.esb.esbapp.model.Challenge;



@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Display name of the user
    private String username;

    private String avatarUrl;

    private int dailyPoints;
    private int weeklyPoints;
    private int totalPoints;

    private double dailyEnergy;
    private double weeklyEnergy;
    private double monthlyEnergy;

    @ManyToMany
    @JoinTable(name = "user_challenges",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "challenge_id"))
    private List<Challenge> joinedChallenges;

    public User() {
    }

    public User(Long id, String username, String avatarUrl, int dailyPoints, int weeklyPoints, int totalPoints) {
        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.dailyPoints = dailyPoints;
        this.weeklyPoints = weeklyPoints;
        this.totalPoints = totalPoints;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    public List<Challenge> getJoinedChallenges() {
        return joinedChallenges;
    }

    public void setJoinedChallenges(List<Challenge> joinedChallenges) {
        this.joinedChallenges = joinedChallenges;
    }
}
