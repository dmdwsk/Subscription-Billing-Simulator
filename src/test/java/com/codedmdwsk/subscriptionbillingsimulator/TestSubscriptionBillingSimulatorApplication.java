package com.codedmdwsk.subscriptionbillingsimulator;

import org.springframework.boot.SpringApplication;

public class TestSubscriptionBillingSimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.from(SubscriptionBillingSimulatorApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
