package com.esb.esbapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private int pointValue;

    private boolean imageRequired;

    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
}
