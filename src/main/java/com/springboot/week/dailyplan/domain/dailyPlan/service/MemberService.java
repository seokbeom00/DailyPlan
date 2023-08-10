package com.springboot.week.dailyplan.domain.dailyPlan.service;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.JoinRequestDto;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.MemberRepository;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.MemberResponseDto;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberResponseDto join(JoinRequestDto joinRequestDto) {
        Member member = new Member();
        member.setName(joinRequestDto.getName());
        member.setEmail(joinRequestDto.getEmail());
        member.setProfileUrl(joinRequestDto.getProfileUrl());
        memberRepository.save(member);
        return MemberResponseDto.builder().id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .planSuccessCount(member.getPlanSuccessCount())
                .challengeSuccessCount(member.getChallengeSuccessCount())
                .build();
    }
}
