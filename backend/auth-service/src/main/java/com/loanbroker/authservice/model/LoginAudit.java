package com.loanbroker.authservice.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "login_audit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // allow null if user later deleted
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "login_time", nullable = false)
    private Instant loginTime;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "success", nullable = false)
    private Boolean success;

    @Column(name = "details", columnDefinition = "jsonb")
    private String details; // optional JSON string; map via ObjectMapper in service

    @PrePersist
    public void prePersist() {
        this.loginTime = Instant.now();
    }
}

