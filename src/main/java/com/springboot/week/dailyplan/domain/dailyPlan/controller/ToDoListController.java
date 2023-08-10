package com.springboot.week.dailyplan.domain.dailyPlan.controller;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.ToDoRequestDto;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.ToDoUpdateDto;
import com.springboot.week.dailyplan.domain.dailyPlan.service.ToDoListService;
import com.springboot.week.dailyplan.global.result.ResultCode;
import com.springboot.week.dailyplan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class ToDoListController {
    private final ToDoListService toDoListService;

    @PostMapping("/{dailyPlanId}")
    public ResponseEntity<ResultResponse> saveTodo(@PathVariable Long dailyPlanId,
                                                   @RequestBody ToDoRequestDto toDoRequestDto){
        Long data = toDoListService.saveTodo(dailyPlanId, toDoRequestDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.SAVE_TODOLIST_SUCCESS, data));
    }
    @PutMapping("/todo/{todoId}")
    public ResponseEntity<ResultResponse> updateTodo(@PathVariable Long todoId, @RequestBody ToDoUpdateDto toDoUpdateDto){
        boolean data = toDoListService.updateTodo(todoId, toDoUpdateDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_TODOLIST_SUCCESS, data));
    }
}
