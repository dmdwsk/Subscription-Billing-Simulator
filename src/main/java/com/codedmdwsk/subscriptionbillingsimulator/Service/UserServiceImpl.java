package com.codedmdwsk.subscriptionbillingsimulator.Service;

import com.codedmdwsk.subscriptionbillingsimulator.Model.User;
import com.codedmdwsk.subscriptionbillingsimulator.Repository.UserRepository;
import com.codedmdwsk.subscriptionbillingsimulator.dto.UserCreateDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.UserResponseDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.UserUpdateDto;
import com.codedmdwsk.subscriptionbillingsimulator.exceptions.DuplicateUserException;
import com.codedmdwsk.subscriptionbillingsimulator.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    public UserResponseDto create(UserCreateDto dto) {
        try {
            User entity = new User();
            entity.setEmail(dto.getEmail());
            entity.setRoles("USER");
            entity.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
            User saved = userRepository.save(entity);
            return UserResponseDto.from(saved);
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            throw new DuplicateUserException( "User with email " + dto.getEmail() + " already exists");
        }
    }
    @Transactional
    @Override
    public UserResponseDto update(Integer id,UserUpdateDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        String newEmail = dto.getEmail();
        boolean emailExist = userRepository.existsByEmail(newEmail,id);
        if(emailExist){
            throw new DuplicateUserException("User with email " + newEmail + " already exists");
        }
        user.setEmail(newEmail);
        userRepository.save(user);
        return UserResponseDto.from(user);
    }

}
