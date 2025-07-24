package com.esb.esbapp.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class TaskRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long taskId;

    private LocalDate completedDate;

    private Integer earnedPoints;

    public TaskRecord() {
    }

    public TaskRecord(Long id, Long userId, Long taskId, LocalDate completedDate, Integer earnedPoints) {
        this.id = id;
        this.userId = userId;
        this.taskId = taskId;
        this.completedDate = completedDate;
        this.earnedPoints = earnedPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    public Integer getEarnedPoints() {
        return earnedPoints;
    }

    public void setEarnedPoints(Integer earnedPoints) {
        this.earnedPoints = earnedPoints;
    }
}
