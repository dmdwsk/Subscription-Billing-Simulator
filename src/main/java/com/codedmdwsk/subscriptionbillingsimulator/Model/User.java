package com.codedmdwsk.subscriptionbillingsimulator.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
@Entity
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames = "email")}
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,length = 255)
    private String email;
    @Column(nullable = false,length = 255)
    private String passwordHash;
    @Column(nullable = false,length = 100)
    private String roles;
    @Column(nullable = false)
    private Instant createdAt;
    @PrePersist
    void onCreate(){
        this.createdAt = Instant.now();
    }
}
