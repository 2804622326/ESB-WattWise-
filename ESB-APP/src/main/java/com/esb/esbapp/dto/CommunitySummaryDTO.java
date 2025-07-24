package com.esb.esbapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunitySummaryDTO {
    private Integer monthAveragePoints;
    private Map<LocalDate, Integer> weeklyAveragePoints;
}
