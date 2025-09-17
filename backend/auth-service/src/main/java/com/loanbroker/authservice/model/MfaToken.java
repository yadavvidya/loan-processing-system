package com.loanbroker.authservice.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "mfa_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MfaToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // store user id as integer; join handled at service layer if needed
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "secret_key", length = 128, nullable = false)
    private String secretKey;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "last_used_at")
    private Instant lastUsedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
        if (this.isActive == null) this.isActive = true;
    }
}
