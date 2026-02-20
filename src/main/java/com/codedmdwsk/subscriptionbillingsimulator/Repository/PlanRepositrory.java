package com.codedmdwsk.subscriptionbillingsimulator.Repository;

import com.codedmdwsk.subscriptionbillingsimulator.Model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepositrory extends JpaRepository<Plan,Integer> {
    List<Plan> findByActiveTrue();
}
