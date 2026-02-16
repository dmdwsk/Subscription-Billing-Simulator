package com.codedmdwsk.subscriptionbillingsimulator.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,length = 255)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PlanPeriod period;
    @Column(nullable = false,precision = 19,scale = 4)
    private BigDecimal price;
    @Column(nullable = false)
    private Boolean active = true;
    @Column(nullable = false)
    private Instant createdAt;
    @Column(nullable = false, length = 3)
    private String currency;
    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
        if (active == null) active = true;
    }
}
