package com.codedmdwsk.subscriptionbillingsimulator.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(
        name = "subscription_events",
        indexes = {
                @Index(name = "idx_se_subscription", columnList = "subscription_id"),
                @Index(name = "idx_se_type", columnList = "type"),
                @Index(name = "idx_se_created_at", columnList = "created_at")
        }
)
public class SubscriptionEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private SubscriptionEventType type;

    // JSON як строка: {"oldStatus":"ACTIVE","newStatus":"GRACE","reason":"invoice unpaid"}
    @Lob
    @Column(name = "payload_json")
    private String payloadJson;

    // хто ініціював (optional): user/system
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_user_id")
    private User actorUser;

    @Column(nullable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
    }
}
