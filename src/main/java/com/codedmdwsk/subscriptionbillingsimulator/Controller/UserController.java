package com.codedmdwsk.subscriptionbillingsimulator.Controller;

import com.codedmdwsk.subscriptionbillingsimulator.Service.UserService;
import com.codedmdwsk.subscriptionbillingsimulator.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Integer id){
        return userService.getById(id);
    }
    @PutMapping("/{id}")

    public UserResponseDto update(@PathVariable Integer id, @Valid @RequestBody UserUpdateDto dto){
        return userService.update(id,dto);
    }
    @PatchMapping("/{id}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable Integer id, @Valid @RequestBody ChangePasswordDto dto){
        userService.changePassword(id,dto);
    }
    @PatchMapping("/{id}/role")
    public UserResponseDto updateRole(@PathVariable Integer id, @Valid @RequestBody UpdateUserRoleDto dto){
        return userService.updateRole(id,dto);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@Valid @RequestBody UserCreateDto userCreateDto){
        return userService.create(userCreateDto);
    }
}
