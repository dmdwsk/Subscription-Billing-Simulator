package com.codedmdwsk.subscriptionbillingsimulator.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    @Email
    @Size(max = 255)
    private String email;
}
