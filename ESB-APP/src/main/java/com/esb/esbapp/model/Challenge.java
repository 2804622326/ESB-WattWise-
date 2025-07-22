package com.esb.esbapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "challenges")
@Data
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private int goalPoints;

    private String reward;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToMany(mappedBy = "joinedChallenges")
    private List<User> participants;
}
