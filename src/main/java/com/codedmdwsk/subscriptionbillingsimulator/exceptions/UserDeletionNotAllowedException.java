package com.codedmdwsk.subscriptionbillingsimulator.exceptions;

import com.codedmdwsk.subscriptionbillingsimulator.Model.User;

public class UserDeletionNotAllowedException extends RuntimeException {
    public UserDeletionNotAllowedException(String message){
        super(message);
    }
}
