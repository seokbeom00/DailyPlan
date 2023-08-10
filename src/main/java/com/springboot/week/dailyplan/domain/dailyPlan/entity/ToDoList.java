package com.springboot.week.dailyplan.domain.dailyPlan.entity;

import com.springboot.week.dailyplan.domain.dailyPlan.entity.DailyPlan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean isComplete;
    private String alarmStartTime;
    private String alarmEndTime;
    @ManyToOne
    @JoinColumn(name = "dailyplan_id")
    private DailyPlan dailyPlan;
}
