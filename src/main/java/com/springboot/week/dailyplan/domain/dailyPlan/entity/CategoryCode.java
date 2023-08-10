package com.springboot.week.dailyplan.domain.dailyPlan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryCode {

    DAILY("일상", "C001"),
    WORK_AND_STUDY("업무/학습", "C002"),
    MEETINGS_AND_APPOINTMENTS("모임/약속", "C003"),
    HEALTH_AND_EXERCISE("건강/운동", "C004"),
    LEISURE_AND_ENTERTAINMENT("여가/오락", "C005"),
    MANAGE_FINANCES("재정 관리", "C006"),
    OTHERS("기타", "C007"),

    ;

    private final String title;
    private final String code;
}