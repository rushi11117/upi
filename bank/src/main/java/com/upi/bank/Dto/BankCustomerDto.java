package com.upi.bank.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * The type Bank customer dto.
 */
@Data
@Builder
public class BankCustomerDto {
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Date dateOfBirth;
}
