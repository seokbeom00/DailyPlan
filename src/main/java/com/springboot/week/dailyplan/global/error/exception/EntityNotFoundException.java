package com.springboot.week.dailyplan.global.error.exception;

import com.springboot.week.dailyplan.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
