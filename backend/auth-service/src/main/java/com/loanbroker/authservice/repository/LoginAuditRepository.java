package com.loanbroker.authservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loanbroker.authservice.model.LoginAudit;

@Repository
public interface LoginAuditRepository extends JpaRepository<LoginAudit, Long> {
}


