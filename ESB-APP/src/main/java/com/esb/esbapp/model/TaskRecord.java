package com.esb.esbapp.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Task task;

    private LocalDateTime completedAt; // 完成时间

    private Integer earnedPoints; // 获得的积分

    private String proofImageUrl; // 打卡证明图片（可选）

    private Boolean verified = true; // 是否验证通过（未来可拓展为AI审核）

}
