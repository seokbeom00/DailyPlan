package com.springboot.week.dailyplan.domain.dailyPlan.controller;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.DailyPlanResponseDto;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.MemberResponseDto;
import com.springboot.week.dailyplan.domain.dailyPlan.service.DailyPlanService;
import com.springboot.week.dailyplan.global.result.ResultCode;
import com.springboot.week.dailyplan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dailyplan")
public class DailyPlanController {
    private final DailyPlanService dailyPlanService;

    @PostMapping("/{member_id}/{date}")
    public ResponseEntity<ResultResponse> save(@RequestParam Long member_id, @RequestParam String date){
        String data = dailyPlanService.save(member_id, date);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.DAILYPLAN_SAVE_SUCCESS, data));
    }
    @GetMapping("/{dailyPlanId}")
    public ResponseEntity<ResultResponse> getPlan(@PathVariable Long dailyPlanId){
        DailyPlanResponseDto data = dailyPlanService.getPlan(dailyPlanId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_DAILYPLAN_SUCCESS, data));
    }
    @DeleteMapping("/{dailyPlanId}")
    public  ResponseEntity<ResultResponse> deletePlan(@PathVariable Long dailyPlanId){
        boolean data = dailyPlanService.deletePlan(dailyPlanId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.DELETE_DAILPLAN_SUCCESS, data));
    }
}
