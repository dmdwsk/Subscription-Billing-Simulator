package com.codedmdwsk.subscriptionbillingsimulator.Service;

import com.codedmdwsk.subscriptionbillingsimulator.dto.plan.PlanCreateDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.plan.PlanResponseDto;

import java.util.List;

public interface PlanService {
    List<PlanResponseDto> getAllPlans();
    PlanResponseDto getPlanById(Integer id);
    PlanResponseDto createPlan(PlanCreateDto dto);
}
