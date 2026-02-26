package com.codedmdwsk.subscriptionbillingsimulator.exceptions;

public class PlanDeletionNotAllowedException extends RuntimeException {
    public PlanDeletionNotAllowedException(String message){
        super(message);
    }
}
