package com.upi.bank.Dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

/**
 * The type Bank customer response dto.
 */
@Data
@Builder
public class BankCustomerResponseDto {

    private String first_name;

    private String last_name;

    private String email;

    private String phoneNumber;
}
