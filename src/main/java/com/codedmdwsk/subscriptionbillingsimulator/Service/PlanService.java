package com.codedmdwsk.subscriptionbillingsimulator.Service;

import com.codedmdwsk.subscriptionbillingsimulator.dto.plan.PlanCreateDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.plan.PlanResponseDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.plan.PlanUpdateDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.user.UpdateUserRoleDto;

import java.util.List;

public interface PlanService {
    List<PlanResponseDto> getAllPlans();
    PlanResponseDto getPlanById(Integer id);
    PlanResponseDto createPlan(PlanCreateDto dto);
    PlanResponseDto updatePlan(Integer id,PlanUpdateDto dto);
    void deletePlan(Integer id);
}
