package com.codedmdwsk.subscriptionbillingsimulator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateDto {

   @NotBlank(message = "User email can not be empty")
   @Size(max = 50,message = "User email can not exceed 50 characters")
   private String email;

    @NotBlank(message = "Password  can not be empty")
    private String password;
}
