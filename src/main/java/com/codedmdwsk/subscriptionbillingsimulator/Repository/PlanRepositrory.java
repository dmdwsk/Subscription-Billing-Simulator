package com.codedmdwsk.subscriptionbillingsimulator.Repository;

import com.codedmdwsk.subscriptionbillingsimulator.Model.Plan;
import com.codedmdwsk.subscriptionbillingsimulator.Model.PlanPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepositrory extends JpaRepository<Plan,Integer> {
    List<Plan> findByActiveTrue();
    boolean existsByNameIgnoreCaseAndPeriod(String name, PlanPeriod period);
}
