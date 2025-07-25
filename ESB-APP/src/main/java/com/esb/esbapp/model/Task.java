package com.esb.esbapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private int rewardPoints;

    public Task(Long id, String title, String description, int rewardPoints) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rewardPoints = rewardPoints;
    }
}
