package com.codedmdwsk.subscriptionbillingsimulator.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "subscription",indexes = {
        @Index(name = "idx_sub_owner",columnList = "owner_id"),
        @Index(name = "idx_sub_plan", columnList = "plan_id"),
        @Index(name = "idx_sub_status", columnList = "status")
})
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "owner_id",nullable = false)
    private User owner;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "plan_id",nullable = false)
    private Plan plan;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SubscriptionStatus status;

    @Column(nullable = false)
    private Instant startDate;


    @Column(name = "current_period_start", nullable = false)
    private Instant currentPeriodStart;

    @Column(name = "current_period_end", nullable = false)
    private Instant currentPeriodEnd;


    @Column(name = "grace_until")
    private Instant graceUntil;


    @Column(name = "terminated_at")
    private Instant terminatedAt;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate(){
        Instant now = Instant.now();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        if(this.status == null){
            this.status = SubscriptionStatus.ACTIVE;
        }
        if(this.startDate == null){
            this.startDate = now;
        }
        if(this.currentPeriodStart == null){
            this.currentPeriodStart = now;
        }
        if(this.currentPeriodEnd == null){
            throw new IllegalStateException("currentPeriodEnd should be set");
        }
    }
    @PreUpdate
    void onUpdate(){
        this.updatedAt = Instant.now();
    }
}
