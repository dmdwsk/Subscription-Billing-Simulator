package com.codedmdwsk.subscriptionbillingsimulator.Service;


import com.codedmdwsk.subscriptionbillingsimulator.dto.user.*;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();
    UserResponseDto getById(Integer id);
    UserResponseDto create(UserCreateDto dto);
    UserResponseDto update(Integer id, UserUpdateDto dto);
    void changePassword(Integer id, ChangePasswordDto dto);
    UserResponseDto updateRole(Integer id, UpdateUserRoleDto dto);
    void delete(Integer id);

}
