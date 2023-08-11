package com.springboot.week.dailyplan.domain.dailyPlan.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDto {
    private Long id;
    private int successToDoCount;
    private int countByToDo;
    private String categoryCode;
}
