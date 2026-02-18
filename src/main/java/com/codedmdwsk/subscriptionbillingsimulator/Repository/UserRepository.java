package com.codedmdwsk.subscriptionbillingsimulator.Repository;

import com.codedmdwsk.subscriptionbillingsimulator.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User>findByEmail(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email,Integer id);
    boolean checkIfNewPasswordIsDiffernet(String password,Integer id);
}
