package com.codedmdwsk.subscriptionbillingsimulator.dto.plan;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlanPatchRequestDto {
    @Size(max = 255)
    private String name;
    private Boolean active;
}
