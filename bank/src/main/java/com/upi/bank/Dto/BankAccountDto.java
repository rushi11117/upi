package com.upi.bank.Dto;

import com.upi.bank.Entity.BankCustomer;
import com.upi.bank.Enums.AccountType;
import lombok.Builder;
import lombok.Data;

/**
 * The type Bank account dto.
 */
@Data
@Builder
public class BankAccountDto {

    private Long accountId;

    private BankCustomer customer;

    private AccountType account_type;

    private Double balance;
}
