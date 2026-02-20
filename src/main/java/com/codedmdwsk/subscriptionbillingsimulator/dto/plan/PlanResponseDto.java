package com.codedmdwsk.subscriptionbillingsimulator.dto.plan;

import com.codedmdwsk.subscriptionbillingsimulator.Model.Plan;
import com.codedmdwsk.subscriptionbillingsimulator.Model.PlanPeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlanResponseDto {
    private Integer id;
    private String name;
    private PlanPeriod period;
    private BigDecimal price;
    private Boolean active;
    private String currency;
    private Instant createdAt;
    public static PlanResponseDto from(Plan plan){
        return new PlanResponseDto(
                plan.getId(),
                plan.getName(),
                plan.getPeriod(),
                plan.getPrice(),
                plan.getActive(),
                plan.getCurrency(),
                plan.getCreatedAt()
        );
    }

}
