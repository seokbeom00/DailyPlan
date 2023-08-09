package com.springboot.week.dailyplan.domain.dailyPlan.service;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.JoinRequestDto;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.MemberRepository;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    MemberRepository memberRepository;
    public Long join(JoinRequestDto joinRequestDto) {
        Member member = new Member();
        member.setName(joinRequestDto.getName());
        member.setEmail(joinRequestDto.getEmail());
        member.setProfileUrl(joinRequestDto.getProfileUrl());
        memberRepository.save(member);
        return member.getId();
    }
}
