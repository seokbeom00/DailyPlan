package com.springboot.week.dailyplan.domain.dailyPlan.controller;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.JoinRequestDto;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.MemberResponseDto;
import com.springboot.week.dailyplan.domain.dailyPlan.service.MemberService;
import com.springboot.week.dailyplan.global.result.ResultCode;
import com.springboot.week.dailyplan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{member_id}")
    public ResponseEntity<ResultResponse> get(@PathVariable Long member_id){
        MemberResponseDto memberResponseDto = memberService.get(member_id);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_USERPROFILE_SUCCESS, memberResponseDto));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ResultResponse> getByEmail(@PathVariable String email){
        MemberResponseDto memberResponseDto = memberService.getByEmail(email);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_USERPROFILE_SUCCESS, memberResponseDto));
    }
}
