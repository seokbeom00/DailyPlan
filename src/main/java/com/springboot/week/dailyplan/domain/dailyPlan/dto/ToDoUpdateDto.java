package com.springboot.week.dailyplan.domain.dailyPlan.dto;

import lombok.Getter;

@Getter
public class ToDoUpdateDto {
    private Long memberId;
    private String title;
    private String alarmStartTime;
    private String alarmEndTime;
    private String beforeCategoryCode;
    private String afterCategoryCode;
    private boolean complete;
}
