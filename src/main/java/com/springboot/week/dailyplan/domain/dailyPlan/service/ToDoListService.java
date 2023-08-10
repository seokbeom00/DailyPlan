package com.springboot.week.dailyplan.domain.dailyPlan.service;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.CategoryRepository;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.DailyPlanRepository;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.ToDoListRepository;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.ToDoRequestDto;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.Category;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.CategoryCode;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.DailyPlan;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.ToDoList;
import com.springboot.week.dailyplan.global.error.ErrorCode;
import com.springboot.week.dailyplan.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

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
        ToDoList toDoList = new ToDoList();
        toDoList.setTitle(toDoRequestDto.getTitle());
        toDoList.setAlarmStartTime(toDoRequestDto.getAlarmStartTime());
        toDoList.setAlarmEndTime(toDoRequestDto.getAlarmEndTime());
        toDoList.setComplete(false);
        toDoList.setDailyPlan(dailyPlan);
        boolean isValidCategoryCode = Arrays.stream(CategoryCode.values())
                .anyMatch(enumValue -> enumValue == toDoRequestDto.getCategoryCode());
        if (!isValidCategoryCode) {
            throw new EntityNotFoundException(ErrorCode.Category_NOT_FOUND,
                    "존재하지 않는 카테고리입니다.");
        }
        Category category = categoryRepository.findByCategoryCode(toDoRequestDto.getCategoryCode())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setCategoryCode(toDoRequestDto.getCategoryCode());
                    newCategory.addTodo(toDoList);
                    return categoryRepository.save(newCategory);
                });
        toDoList.setCategory(category);
        toDoListRepository.save(toDoList);
        return toDoList.getId();
    }
}
