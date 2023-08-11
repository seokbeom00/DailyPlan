package com.springboot.week.dailyplan.domain.dailyPlan.service;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.CategoryRepository;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.CategoryResponseDto;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.MemberRepository;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.TodoResponseDto;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.Category;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.Member;
import com.springboot.week.dailyplan.global.error.ErrorCode;
import com.springboot.week.dailyplan.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final MemberRepository memberRepository;
    @Transactional
    public List<CategoryResponseDto> getList(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND,
                        "해당 id의 유저가 없습니다. id: " + memberId));
        List<Category> categoryList = member.getCategoryList();
        return categoryList.stream()
                .map(category -> {
                    CategoryResponseDto dto = new CategoryResponseDto();
                    dto.setId(category.getId());
                    dto.setSuccessToDoCount(category.getSuccessToDoCount());
                    dto.setCountByToDo(category.getCountByToDo());
                    dto.setCategoryCode(category.getCategoryCode().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Transactional
    public List<CategoryResponseDto> getBestList(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND,
                        "해당 id의 유저가 없습니다. id: " + memberId));
        List<Category> categoryList = member.getCategoryList();
        return categoryList.stream()
                .sorted(Comparator.comparingInt(Category::getSuccessToDoCount).reversed())
                .limit(3) // 상위 3개만 선택
                .map(category -> {
                    CategoryResponseDto dto = new CategoryResponseDto();
                    dto.setId(category.getId());
                    dto.setSuccessToDoCount(category.getSuccessToDoCount());
                    dto.setCountByToDo(category.getCountByToDo());
                    dto.setCategoryCode(category.getCategoryCode().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<CategoryResponseDto> getWorstList(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND,
                        "해당 id의 유저가 없습니다. id: " + memberId));
        List<Category> categoryList = member.getCategoryList();

        List<Category> sortedList = categoryList.stream()
                .sorted(Comparator.comparingInt(Category::getSuccessToDoCount).reversed())
                .collect(Collectors.toList());

        int start = Math.max(sortedList.size() - 3, 0);
        List<Category> bottomThree = sortedList.subList(start, sortedList.size());

        return bottomThree.stream()
                .map(category -> {
                    CategoryResponseDto dto = new CategoryResponseDto();
                    dto.setId(category.getId());
                    dto.setSuccessToDoCount(category.getSuccessToDoCount());
                    dto.setCountByToDo(category.getCountByToDo());
                    dto.setCategoryCode(category.getCategoryCode().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
