package com.esb.esbapp.model;



/**
 * Simple DTO representing a leaderboard entry.
 */
public class LeaderboardEntry {

    private String userName;

    private Integer score;

    private String communityId;

    public LeaderboardEntry() {
    }

    public LeaderboardEntry(String userName, Integer score, String communityId) {
        this.userName = userName;
        this.score = score;
        this.communityId = communityId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }
}
