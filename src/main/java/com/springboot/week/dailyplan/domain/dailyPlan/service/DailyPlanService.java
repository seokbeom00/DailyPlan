package com.springboot.week.dailyplan.domain.dailyPlan.service;

import com.springboot.week.dailyplan.domain.dailyPlan.dto.DailyPlanRepository;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.DailyPlanResponseDto;
import com.springboot.week.dailyplan.domain.dailyPlan.dto.MemberRepository;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.DailyPlan;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.Member;
import com.springboot.week.dailyplan.global.error.ErrorCode;
import com.springboot.week.dailyplan.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyPlanService {
    private final DailyPlanRepository dailyPlanRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public String save(Long id, String date) {
        DailyPlan dailyPlan = new DailyPlan();
        dailyPlan.setYearmonth(date.substring(0, 6));
        dailyPlan.setDate(date.substring(6));
        dailyPlanRepository.findByYearmonthAndDate(date.substring(0, 6), date.substring(6))
                        .ifPresent(m-> {
                            throw new EntityNotFoundException(ErrorCode.DAILYPLAN_ALREADY_EXIST,
                                    "이미 해당 날짜의 플랜이 있습니다. date: "+date);
                        });
        dailyPlanRepository.save(dailyPlan);
        Member member = memberRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND,
                        "해당 id의 유저가 없습니다. id: " + id));
        dailyPlan.setMember(member);
        member.addDailyPlan(dailyPlan);
        return date;
    }

    @Transactional
    public DailyPlanResponseDto getPlan(Long dailyPlanId) {
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.DAILYPLAN_NOT_FOUND,
                        "해당하는 id의 데일리 플랜이 없습니다. id: "+ dailyPlanId));
        return DailyPlanResponseDto.builder()
                .yearmonth(dailyPlan.getYearmonth())
                .date(dailyPlan.getDate())
                .toDoLists(dailyPlan.getToDoLists())
                .build();
    }
    @Transactional
    public boolean deletePlan(Long dailyPlanId) {
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.DAILYPLAN_NOT_FOUND,
                        "해당하는 id의 데일리 플랜이 없습니다. id: "+ dailyPlanId));
        dailyPlanRepository.delete(dailyPlan);
        return true;
    }

    public List<DailyPlanResponseDto> getMonthPlan(Long memberId, String month) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND,
                        "해당 id의 유저가 없습니다. id: " + memberId));
        List<DailyPlan> dailyPlans = member.getDailyPlanList();
        String finalMonth;
        if(month.length() == 1){
            finalMonth = "0" + month;
        } else {
            finalMonth = month;
        }
        return dailyPlans.stream()
                .filter(dailyPlan -> dailyPlan.getYearmonth().endsWith(finalMonth))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private DailyPlanResponseDto convertToDto(DailyPlan dailyPlan) {
        return DailyPlanResponseDto.builder()
                .yearmonth(dailyPlan.getYearmonth())
                .date(dailyPlan.getDate())
                .toDoLists(new ArrayList<>(dailyPlan.getToDoLists())) // creating a new list to ensure immutability
                .build();
    }
}
