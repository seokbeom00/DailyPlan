package com.springboot.week.dailyplan.domain.dailyPlan.dto;

import com.springboot.week.dailyplan.domain.dailyPlan.entity.ToDoList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class DailyPlanResponseDto {
    private String yearmonth;
    private String date;
    private List<ToDoList> toDoLists = new ArrayList<>();

    @Builder
    public DailyPlanResponseDto(String yearmonth, String date, List<ToDoList> toDoLists){
        this.yearmonth = yearmonth;
        this.date = date;
        this.toDoLists = toDoLists;
    }
}
