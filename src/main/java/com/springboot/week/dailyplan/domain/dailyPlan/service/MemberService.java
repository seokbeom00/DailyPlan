package com.springboot.week.dailyplan.domain.dailyPlan.service;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.JoinRequestDto;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.MemberRepository;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.MemberResponseDto;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.Member;
import com.springboot.week.dailyplan.global.error.ErrorCode;
import com.springboot.week.dailyplan.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
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
    @Transactional
    public MemberResponseDto get(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND,
                        "해당 id의 유저가 없습니다 : "+memberId));
        return MemberResponseDto.builder().id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .planSuccessCount(member.getPlanSuccessCount())
                .challengeSuccessCount(member.getChallengeSuccessCount())
                .build();
    }
    @Transactional
    public MemberResponseDto getByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()->new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND,
                        "해당 email의 유저가 없습니다 : "+email));
        return MemberResponseDto.builder().id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .planSuccessCount(member.getPlanSuccessCount())
                .challengeSuccessCount(member.getChallengeSuccessCount())
                .build();
    }
}
