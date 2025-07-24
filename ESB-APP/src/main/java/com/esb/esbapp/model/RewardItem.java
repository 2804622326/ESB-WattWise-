package com.esb.esbapp.model;

/**
 * Reward item entity.
 */
import jakarta.persistence.*;

@Entity
public class RewardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer costPoints;

    private String imageUrl;

    private Integer stock;

    public RewardItem() {
    }

    public RewardItem(Long id, String name, String description, Integer costPoints, String imageUrl, Integer stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.costPoints = costPoints;
        this.imageUrl = imageUrl;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCostPoints() {
        return costPoints;
    }

    public void setCostPoints(Integer costPoints) {
        this.costPoints = costPoints;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
