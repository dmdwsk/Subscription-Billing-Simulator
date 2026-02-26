package com.codedmdwsk.subscriptionbillingsimulator.Service;

import com.codedmdwsk.subscriptionbillingsimulator.Model.Plan;
import com.codedmdwsk.subscriptionbillingsimulator.Repository.PlanRepositrory;
import com.codedmdwsk.subscriptionbillingsimulator.dto.plan.PlanCreateDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.plan.PlanResponseDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.plan.PlanUpdateDto;
import com.codedmdwsk.subscriptionbillingsimulator.exceptions.DuplicatePlanException;
import com.codedmdwsk.subscriptionbillingsimulator.exceptions.DuplicateUserException;
import com.codedmdwsk.subscriptionbillingsimulator.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        String name = dto.getName().trim();
        if (planRepositrory.existsByNameIgnoreCaseAndPeriod(name, dto.getPeriod())) {
            throw new DuplicatePlanException(
                    "Plan with name '" + name + "' and period '" + dto.getPeriod() + "' already exists"
            );
        }

        Plan plan = new Plan();
        plan.setName(name);
        plan.setPeriod(dto.getPeriod());
        plan.setPrice(dto.getPrice());
        plan.setCurrency(dto.getCurrency().trim().toUpperCase());

        Plan saved = planRepositrory.save(plan);
        return PlanResponseDto.from(saved);
    }

    @Override
    public PlanResponseDto updatePlan(Integer id,PlanUpdateDto dto) {
        Plan plan = planRepositrory.findById(
                id).
                orElseThrow(()-> new NotFoundException("Plan not found"));
        if (dto.getPrice() == plan.getPrice() && dto.getActive() == plan.getActive()){
            return PlanResponseDto.from(plan);
        }


    }
}
