package com.springboot.week.dailyplan.domain.dailyPlan.controller;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.CategoryResponseDto;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.Category;
import com.springboot.week.dailyplan.domain.dailyPlan.service.CategoryService;
import com.springboot.week.dailyplan.global.result.ResultCode;
import com.springboot.week.dailyplan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/list{memberId}")
    public List<CategoryResponseDto> getList(@RequestParam Long member_id){
        return categoryService.getList(member_id);
    }
    @GetMapping("/bestlist/{memberId}")
    public List<CategoryResponseDto> getBestList(@RequestParam Long member_id){
        return categoryService.getBestList(member_id);
    }
    @GetMapping("/worstList/{memberId}")
    public List<CategoryResponseDto> getWorstList(@RequestParam Long member_id){
        return categoryService.getWorstList(member_id);
    }
}
