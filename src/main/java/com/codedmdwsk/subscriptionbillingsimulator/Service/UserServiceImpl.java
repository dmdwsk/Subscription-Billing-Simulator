package com.codedmdwsk.subscriptionbillingsimulator.Service;

import com.codedmdwsk.subscriptionbillingsimulator.Model.User;
import com.codedmdwsk.subscriptionbillingsimulator.Repository.UserRepository;
import com.codedmdwsk.subscriptionbillingsimulator.dto.UserCreateDto;
import com.codedmdwsk.subscriptionbillingsimulator.dto.UserResponseDto;
import com.codedmdwsk.subscriptionbillingsimulator.exceptions.DuplicateUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public UserResponseDto create(UserCreateDto dto) {
        try {
            User entity = new User();
            entity.setEmail(dto.getEmail());
            entity.setRoles("USER");
            User saved = userRepository.save(entity);
            return UserResponseDto.from(saved);
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            throw new DuplicateUserException( "User with email " + dto.getEmail() + " already exists");
        }
    }
}
