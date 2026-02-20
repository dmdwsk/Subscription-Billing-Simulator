package com.codedmdwsk.subscriptionbillingsimulator.dto.user;

import com.codedmdwsk.subscriptionbillingsimulator.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
     private Integer id;
     private String role;
     private String email;
     private Instant createdAt;
     private Instant updatedAt;
     public static UserResponseDto from(User user){
          return new UserResponseDto(
                  user.getId(),
                  user.getRole(),
                  user.getEmail(),
                  user.getCreatedAt(),
                  user.getUpdatedAt()
          );
     }
}
