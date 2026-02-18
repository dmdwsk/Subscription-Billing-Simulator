package com.codedmdwsk.subscriptionbillingsimulator.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
