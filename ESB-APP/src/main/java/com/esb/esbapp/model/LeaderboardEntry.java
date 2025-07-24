package com.esb.esbapp.model;

/**
 * Leaderboard entry entity.
 */

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaderboardEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private int score; // 积分值（可以是当天、当周或全部）

    private String type; // 排名类型：day、week、all

    private LocalDate date; // 排行榜的时间节点（仅 day/week 用）

    private String communityId; // 用于分组排名（例如 CORK-T23）

}
