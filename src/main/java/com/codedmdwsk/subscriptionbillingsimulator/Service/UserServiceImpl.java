package com.codedmdwsk.subscriptionbillingsimulator.Service;

import com.codedmdwsk.subscriptionbillingsimulator.Model.User;
import com.codedmdwsk.subscriptionbillingsimulator.Repository.UserRepository;
import com.codedmdwsk.subscriptionbillingsimulator.dto.*;
import com.codedmdwsk.subscriptionbillingsimulator.exceptions.DuplicateUserException;
import com.codedmdwsk.subscriptionbillingsimulator.exceptions.NotFoundException;
import com.codedmdwsk.subscriptionbillingsimulator.exceptions.UserDeletionNotAllowedException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::from)
                .toList();
    }

    @Override
    public UserResponseDto getById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        return UserResponseDto.from(user);
    }

    @Override
    public UserResponseDto create(UserCreateDto dto) {
        try {
            User entity = new User();
            entity.setEmail(dto.getEmail());
            entity.setRole("USER");
            entity.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
            User saved = userRepository.save(entity);
            return UserResponseDto.from(saved);
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            throw new DuplicateUserException( "User with email " + dto.getEmail() + " already exists");
        }
    }
    @Transactional
    @Override
    public UserResponseDto update(Integer me,UserUpdateDto dto) {
        User user = userRepository.findById(me)
                .orElseThrow(() -> new NotFoundException("User not found"));
        String newEmail = dto.getEmail();
        if(newEmail != null) {
            newEmail = newEmail.trim();
            if(!newEmail.isBlank() && !newEmail.equalsIgnoreCase(user.getEmail())){
                boolean emailExist = userRepository.existsByEmailIgnoreCaseAndIdNot(newEmail, me);
                if (emailExist) {
                    throw new DuplicateUserException("User with email " + newEmail + " already exists");
                }
                user.setEmail(newEmail);
            }
        }
        return UserResponseDto.from(user);
    }

    @Override
    public void changePassword(Integer id, ChangePasswordDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        boolean ok = passwordEncoder.matches(dto.getCurrentPassword(),user.getPasswordHash());
        if(!ok){
            throw new IllegalArgumentException("Current password is incorrect");
        }

    }

    @Override
    public UserResponseDto updateRole(Integer id, UpdateUserRoleDto dto) {
        return null;
    }

    @Override
    public void delete(Integer me) {
        try {
            userRepository.deleteById(me);
        }catch (DataIntegrityViolationException e){
            throw new UserDeletionNotAllowedException("Cannot delete user with existing subscription");
        }
    }
}
