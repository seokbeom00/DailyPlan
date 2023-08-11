package com.springboot.week.dailyplan.domain.dailyPlan.controller;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.ToDoRequestDto;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.ToDoUpdateDto;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.TodoResponseDto;
import com.springboot.week.dailyplan.domain.dailyPlan.service.ToDoListService;
import com.springboot.week.dailyplan.global.result.ResultCode;
import com.springboot.week.dailyplan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/{todoId}")
    public ResponseEntity<ResultResponse> updateTodo(@PathVariable Long todoId, @RequestBody ToDoUpdateDto toDoUpdateDto){
        boolean data = toDoListService.updateTodo(todoId, toDoUpdateDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_TODOLIST_SUCCESS, data));
    }
    @GetMapping("/{todoId}")
    public ResponseEntity<ResultResponse> getTodo(@PathVariable Long todoId){
        TodoResponseDto data = toDoListService.getTodo(todoId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_TODOLIST_SUCCESS, data));
    }
    @DeleteMapping("/{todoId}")
    public ResponseEntity<ResultResponse> deleteTodo(@PathVariable Long todoId){
        boolean data = toDoListService.deleteTodo(todoId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.DELETE_TODOLIST_SUCCESS, data));
    }
    @GetMapping("/{dailyplanId}/list")
    public ResponseEntity<ResultResponse> getListTodo(@PathVariable Long dailyplanId){
        List<TodoResponseDto> data = toDoListService.getListTodo(dailyplanId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_TODOLIST_ALL_SUCCESS, data));
    }
    @PostMapping("/complete/{todoId}")
    public ResponseEntity<ResultResponse> completeTodo(@PathVariable Long todoId){
        boolean data = toDoListService.completeTodo(todoId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.COMPLETE_TODOLIST_SUCCESS, data));
    }
    @PostMapping("/fail/{todoId}")
    public ResponseEntity<ResultResponse> failTodo(@PathVariable Long todoId){
        boolean data = toDoListService.failTodo(todoId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.FAIL_TODOLIST_SUCCESS, data));
    }
}
