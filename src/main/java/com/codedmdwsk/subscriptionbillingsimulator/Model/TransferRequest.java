package com.codedmdwsk.subscriptionbillingsimulator.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "transfer_requests",
indexes = {
        @Index(name = "idx_tr_subscription", columnList = "subscription_id"),
        @Index(name = "idx_tr_status", columnList = "status"),
        @Index(name = "idx_tr_from_user", columnList = "from_user_id"),
        @Index(name = "idx_tr_to_user", columnList = "to_user_id")
})
public class TransferRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_user_id", nullable = false)
    private User fromUser;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "to_user_id", nullable = false)
    private User toUser;


    @Column(name = "transfer_date", nullable = false)
    private Instant transferDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransferStatus status;


    @Column(length = 500)
    private String reason;

    @Column(name = "requested_at", nullable = false)
    private Instant requestedAt;

    @Column(name = "applied_at")
    private Instant appliedAt;

    @PrePersist
    void onCreate() {
        this.requestedAt = Instant.now();
        if (this.status == null) {
            this.status = TransferStatus.REQUESTED;
        }
    }
}
