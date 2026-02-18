package com.codedmdwsk.subscriptionbillingsimulator.Service;


import com.codedmdwsk.subscriptionbillingsimulator.dto.UserCreateDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.UserResponseDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();
    UserResponseDto create(UserCreateDto dto);
    UserResponseDto update(Integer id,UserUpdateDto dto);
    void delete(Integer id);

}
