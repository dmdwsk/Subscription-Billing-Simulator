package com.codedmdwsk.subscriptionbillingsimulator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {
   @Email
   @NotBlank(message = "User email can not be empty")
   @Size(max = 250)
   private String email;

    @NotBlank(message = "Password  can not be empty")
    @Size(min = 8,max = 72)
    private String password;
}
