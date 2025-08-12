package com.loanbroker.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loanbroker.authservice.model.MfaToken;

@Repository
public interface MfaTokenRepository extends JpaRepository<MfaToken, Long> {
    Optional<MfaToken> findByUserId(Long userId);
}
