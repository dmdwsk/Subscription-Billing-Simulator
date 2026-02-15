package com.codedmdwsk.subscriptionbillingsimulator.Model;

public enum SubscriptionEventType {
    CREATED,
    STATUS_CHANGED,
    BILLED,
    INVOICE_ISSUED,
    INVOICE_PAID,
    INVOICE_FAILED,
    TRANSFER_REQUESTED,
    TRANSFER_APPLIED,
    TRANSFER_REJECTED,
    CANCELLED
}

