package com.springboot.week.dailyplan.domain.dailyPlan.controller;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.JoinRequestDto;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.MemberResponseDto;
import com.springboot.week.dailyplan.domain.dailyPlan.service.MemberService;
import com.springboot.week.dailyplan.global.result.ResultCode;
import com.springboot.week.dailyplan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("")
    public ResponseEntity<ResultResponse> join(@RequestBody JoinRequestDto joinRequestDto){
        MemberResponseDto memberResponseDto = memberService.join(joinRequestDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_SAVE_OR_UPDATE_SUCCESS, memberResponseDto));
    }
}
