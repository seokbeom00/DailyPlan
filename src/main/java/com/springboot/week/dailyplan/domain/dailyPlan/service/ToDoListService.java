package com.springboot.week.dailyplan.domain.dailyPlan.service;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.*;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.*;
import com.springboot.week.dailyplan.global.error.ErrorCode;
import com.springboot.week.dailyplan.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ToDoListService {
    private final DailyPlanRepository dailyPlanRepository;
    private final ToDoListRepository toDoListRepository;
    private final CategoryRepository categoryRepository;
    @Transactional
    public Long saveTodo(Long dailyPlanId, ToDoRequestDto toDoRequestDto) {
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.DAILYPLAN_NOT_FOUND,
                        "해당하는 id의 데일리 플랜이 없습니다. id: "+ dailyPlanId));
        Member member =dailyPlan.getMember();
        ToDoList toDoList = new ToDoList();
        toDoList.setTitle(toDoRequestDto.getTitle());
        toDoList.setAlarmStartTime(toDoRequestDto.getAlarmStartTime());
        toDoList.setAlarmEndTime(toDoRequestDto.getAlarmEndTime());
        toDoList.setComplete(false);
        toDoList.setDailyPlan(dailyPlan);
        CategoryCode cate = CategoryCode.findByCode(toDoRequestDto.getCategoryCode());
        Category category = categoryRepository.findByCategoryCode(cate)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setCategoryCode(cate);
                    newCategory.addTodo(toDoList);
                    newCategory.setMember(member);
                    return categoryRepository.save(newCategory);
                });
        category.addTodo(toDoList);
        category.setMember(member);
        toDoList.setCategory(category);
        toDoList.setDailyPlan(dailyPlan);
        toDoListRepository.save(toDoList);
        dailyPlan.addTodo(toDoList);
        member.addCategory(category);
        return toDoList.getId();
    }

    public boolean updateTodo(Long todoId, ToDoUpdateDto toDoUpdateDto) {
        CategoryCode cate = CategoryCode.findByCode(toDoUpdateDto.getAfterCategoryCode());
        
    }
}
