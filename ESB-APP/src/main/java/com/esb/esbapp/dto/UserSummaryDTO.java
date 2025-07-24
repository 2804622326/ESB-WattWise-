package com.esb.esbapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDTO {
    private Integer monthPoints;
    private Map<LocalDate, Integer> weeklyPoints;
}
