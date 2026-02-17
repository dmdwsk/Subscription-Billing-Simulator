package com.codedmdwsk.subscriptionbillingsimulator.dto;

import com.codedmdwsk.subscriptionbillingsimulator.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor// Коли Hibernate витягує дані з бази, він спочатку створює пустий об'єкт через цей конструктор.
public class UserResponseDto {
     private Integer id;
     private String roles;
     private String email;
     private Instant createdAt;
     public static UserResponseDto from(User user){
          return new UserResponseDto(
                  user.getId(),
                  user.getRoles(),
                  user.getEmail(),
                  user.getCreatedAt()
          );
     }
}
