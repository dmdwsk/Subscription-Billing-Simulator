package com.codedmdwsk.subscriptionbillingsimulator.Service;


import com.codedmdwsk.subscriptionbillingsimulator.dto.UserCreateDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.UserResponseDto;

public interface UserService {
   UserResponseDto create(UserCreateDto dto);
}
