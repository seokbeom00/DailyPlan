package com.springboot.week.dailyplan.domain.dailyPlan.dto;

import com.springboot.week.dailyplan.domain.dailyPlan.entity.CategoryCode;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.DailyPlan;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoRequestDto {
    private String title;
    private String alarmStartTime;
    private String alarmEndTime;
    private String categoryCode;
}
