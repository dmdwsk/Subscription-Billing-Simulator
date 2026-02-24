package com.codedmdwsk.subscriptionbillingsimulator.dto.plan;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlanUpdateDto {
    @NotNull
    @DecimalMin(value = "0.00",inclusive = false)
    private BigDecimal price;
    @NotNull
    private Boolean active;
}
