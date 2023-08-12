package com.springboot.week.dailyplan.domain.dailyPlan.service;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.*;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.*;
import com.springboot.week.dailyplan.global.error.ErrorCode;
import com.springboot.week.dailyplan.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoListService {
    private final DailyPlanRepository dailyPlanRepository;
    private final ToDoListRepository toDoListRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
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
    @Transactional
    public boolean updateTodo(Long todoId, ToDoUpdateDto toDoUpdateDto) {
        CategoryCode cate = CategoryCode.findByCode(toDoUpdateDto.getAfterCategoryCode());
        CategoryCode before = CategoryCode.findByCode(toDoUpdateDto.getBeforeCategoryCode());
        Member member = memberRepository.findById(toDoUpdateDto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND,
                        "해당 id로 멤버를 찾을 수 없습니다."));
        ToDoList toDoList = toDoListRepository.findById(todoId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.TODOLIST_NOT_FOUND,
                        "해당 하는 투두 리스트가 없습니다."));
        List<Category> categoryList = member.getCategoryList();
        Optional<Category> haveCategory = categoryList.stream()
                .filter(category -> category.getCategoryCode().equals(before))
                .findFirst();
        if(haveCategory.isPresent()){
            Category beforecategory = haveCategory.get();
            beforecategory.setCategoryCode(cate);
            toDoList.setCategory(beforecategory);

            toDoList.setTitle(toDoUpdateDto.getTitle());
            toDoList.setAlarmStartTime(toDoUpdateDto.getAlarmStartTime());
            toDoList.setAlarmEndTime(toDoUpdateDto.getAlarmEndTime());
            toDoList.setComplete(toDoUpdateDto.isComplete());
        }else {
            throw new EntityNotFoundException(ErrorCode.Category_NOT_FOUND,
                    "해당 유저"+toDoUpdateDto.getMemberId()+"는 "+toDoUpdateDto.getBeforeCategoryCode()+
                            "에 해당하는 카테고리를 가지고 있지 않습니다.");
        }
        return true;
    }
    @Transactional
    public TodoResponseDto getTodo(Long todoId) {
        ToDoList toDoList = toDoListRepository.findById(todoId)
                .orElseThrow(()-> new EntityNotFoundException(ErrorCode.TODOLIST_NOT_FOUND,
                        "해당 Id에 해당하는 투두리스트가 없습니다 : "+todoId));
        TodoResponseDto todoResponseDto = new TodoResponseDto();
        todoResponseDto.setId(toDoList.getId());
        todoResponseDto.setAlarmStartTime(toDoList.getAlarmStartTime());
        todoResponseDto.setAlarmEndTime(toDoList.getAlarmEndTime());
        todoResponseDto.setComplete(toDoList.isComplete());
        todoResponseDto.setTitle(toDoList.getTitle());
        todoResponseDto.setCategoryCode(toDoList.getCategory().getCategoryCode().toString());
        return todoResponseDto;
    }
    @Transactional
    public boolean deleteTodo(Long todoId) {
        ToDoList toDoList = toDoListRepository.findById(todoId)
                .orElseThrow(()-> new EntityNotFoundException(ErrorCode.TODOLIST_NOT_FOUND,
                        "해당 Id에 해당하는 투두리스트가 없습니다 : "+todoId));
        toDoListRepository.delete(toDoList);
        toDoList.getCategory().subTodo();
        return true;
    }
    @Transactional
    public List<TodoResponseDto> getListTodo(Long dailyplanId) {
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyplanId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.DAILYPLAN_NOT_FOUND,
                        "해당하는 id의 데일리 플랜이 없습니다. id: "+ dailyplanId));
        List<ToDoList> toDoLists = dailyPlan.getToDoLists();
        return toDoLists.stream()
                .map(toDoList -> {
                    TodoResponseDto dto = new TodoResponseDto();
                    dto.setId(toDoList.getId());
                    dto.setComplete(toDoList.isComplete());
                    dto.setTitle(toDoList.getTitle());
                    dto.setAlarmStartTime(toDoList.getAlarmStartTime());
                    dto.setAlarmEndTime(toDoList.getAlarmEndTime());
                    dto.setCategoryCode(toDoList.getCategory().getCategoryCode().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Transactional
    public boolean completeTodo(Long todoId) {
        ToDoList toDoList = toDoListRepository.findById(todoId)
                .orElseThrow(()-> new EntityNotFoundException(ErrorCode.TODOLIST_NOT_FOUND,
                        "해당 Id에 해당하는 투두리스트가 없습니다 : "+todoId));
        toDoList.setComplete(true);
        toDoList.getCategory().addSuccess();
        Member member = memberRepository.findById(toDoList.getDailyPlan().getMember().getId())
                .orElseThrow(()-> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND,
                        "해당 투두리스트에 해당하는 멤버가 없습니다 "));
        member.addPlan();
        member.addChallenge();
        return true;
    }
    @Transactional
    public boolean failTodo(Long todoId) {
        ToDoList toDoList = toDoListRepository.findById(todoId)
                .orElseThrow(()-> new EntityNotFoundException(ErrorCode.TODOLIST_NOT_FOUND,
                        "해당 Id에 해당하는 투두리스트가 없습니다 : "+todoId));
        toDoList.setComplete(false);
        toDoList.getCategory().subSuccess();
        return true;
    }
}
