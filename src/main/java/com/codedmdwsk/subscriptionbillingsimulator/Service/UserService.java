package com.codedmdwsk.subscriptionbillingsimulator.Service;


import com.codedmdwsk.subscriptionbillingsimulator.dto.UserCreateDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();
    UserResponseDto create(UserCreateDto dto);

}
