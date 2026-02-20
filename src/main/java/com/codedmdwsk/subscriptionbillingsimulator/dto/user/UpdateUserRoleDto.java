package com.codedmdwsk.subscriptionbillingsimulator.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRoleDto {
    @NotBlank
    private String role;
}
