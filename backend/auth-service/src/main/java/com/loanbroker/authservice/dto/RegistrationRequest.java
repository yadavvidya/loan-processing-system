package com.loanbroker.authservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    // Basic User Info
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;

    // KYC Info
    private String panNumber;
    private LocalDate dateOfBirth;
    private String bankAccountNo;
    private String ifscCode;
    private BigDecimal annualIncome;
}
