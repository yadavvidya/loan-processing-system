package com.loanbroker.authservice.model;


import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_kyc", uniqueConstraints = @UniqueConstraint(columnNames = {"pan_number"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserKyc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // link to users table (no cross-db enforcement)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "pan_number", length = 10, nullable = false)
    private String panNumber;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "bank_account_no", length = 34, nullable = false)
    private String bankAccountNo;

    @Column(name = "ifsc_code", length = 11, nullable = false)
    private String ifscCode;

    @Column(name = "annual_income", precision = 15, scale = 2, nullable = false)
    private java.math.BigDecimal annualIncome;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}

