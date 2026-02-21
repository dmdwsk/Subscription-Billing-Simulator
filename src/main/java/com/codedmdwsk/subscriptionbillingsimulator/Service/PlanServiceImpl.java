package com.codedmdwsk.subscriptionbillingsimulator.Service;

import com.codedmdwsk.subscriptionbillingsimulator.Model.Plan;
import com.codedmdwsk.subscriptionbillingsimulator.Repository.PlanRepositrory;
import com.codedmdwsk.subscriptionbillingsimulator.dto.plan.PlanCreateDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.plan.PlanResponseDto;
import com.codedmdwsk.subscriptionbillingsimulator.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepositrory planRepositrory;

    @Override
    public List<PlanResponseDto> getAllPlans() {
        return planRepositrory.findAll()
                .stream()
                .map(PlanResponseDto::from)
                .toList();
    }

    @Override
    public PlanResponseDto getPlanById(Integer id) {
        Plan plan = planRepositrory.findById(id)
                .orElseThrow(() -> new NotFoundException("Plan not found"));
        return PlanResponseDto.from(plan);
    }

    @Override
    public PlanResponseDto createPlan(PlanCreateDto dto) {
        try {

        }catch (DataIntegrityViolationException dataIntegrityViolationException){

        }
    }


}
