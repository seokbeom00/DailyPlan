package com.springboot.week.dailyplan.domain.dailyPlan.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoResponseDto {
    private Long id;
    private boolean complete;
    private String title;
    private String alarmStartTime;
    private String alarmEndTime;
    private String categoryCode;
}
