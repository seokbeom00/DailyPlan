package com.springboot.week.dailyplan.domain.dailyPlan.entity;

import com.springboot.week.dailyplan.global.error.ErrorCode;
import com.springboot.week.dailyplan.global.error.exception.EntityNotFoundException;
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

    public static CategoryCode findByCode(String code){
        CategoryCode categoryCode;
        switch (code){
            case "C001" : return DAILY;
            case "C002" : return WORK_AND_STUDY;
            case "C003" : return MEETINGS_AND_APPOINTMENTS;
            case "C004" : return HEALTH_AND_EXERCISE;
            case "C005" : return LEISURE_AND_ENTERTAINMENT;
            case "C006" : return MANAGE_FINANCES;
            case "C007" : return OTHERS;
        }
        throw new EntityNotFoundException(ErrorCode.Category_NOT_FOUND, code + "는 존재하지 않는 카테고리입니다");
    }
}