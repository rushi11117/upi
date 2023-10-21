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
    private String first_name;

    private String last_name;

    private String email;

    private String phoneNumber;

    private Date dateOfBirth;

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
