package com.loanbroker.authservice.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loanbroker.authservice.model.UserKyc;


@Repository
public interface UserKycRepository extends JpaRepository<UserKyc, Long> {

    Optional<UserKyc> findByPanNumber(String panNumber);

    
}
