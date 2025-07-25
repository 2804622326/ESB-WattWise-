package com.esb.esbapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Builder
public class RewardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int costPoints;
    private String imageUrl;

    public RewardItem(Long id, String name, String description, int costPoints,
                       String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.costPoints = costPoints;
        this.imageUrl = imageUrl;
    }
}
