package com.esb.esbapp.model;

/**
 * Reward item entity.
 */
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 奖励名称，例如 €5 Voucher

    private String description; // 奖励说明，例如 “€5 off at Lidl”

    private Integer cost; // 需要消耗的积分

    private Integer stock; // 当前库存数量

    private String imageUrl; // 奖品图片链接（用于前端展示）

    private Boolean active = true; // 是否仍在兑换中

}
