package com.codedmdwsk.subscriptionbillingsimulator.dto.plan;

import com.codedmdwsk.subscriptionbillingsimulator.Model.PlanPeriod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class PlanCreateDto {
    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    private PlanPeriod period;

    @NotNull
    @DecimalMin(value = "0.0001")
    private BigDecimal price;

    @NotBlank
    @Size(min = 3,max = 3)
    private String currency;

}
