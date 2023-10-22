package com.upi.bank.Dto.DtoServices;

import com.upi.bank.Dto.BankAccountDto;
import com.upi.bank.Entity.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Bank account dto services.
 */
@Service
public class BankAccountDtoServices {

    /**
     * Map bank account dto to bank account bank account.
     *
     * @param bankAccountDto the bank account dto
     *
     * @return the bank account
     */
    public BankAccount mapBankAccountDtoToBankAccount(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccount_type(bankAccountDto.getAccount_type());
        bankAccount.setAccountId(bankAccountDto.getAccountId());
        bankAccount.setBalance(bankAccountDto.getBalance());
        return bankAccount;
    }

    /**
     * Map bank account to bank account dto bank account dto.
     *
     * @param bankAccount the bank account
     *
     * @return the bank account dto
     */
    public BankAccountDto mapBankAccountToBankAccountDto (BankAccount bankAccount) {
        return BankAccountDto.builder()
                .accountId(bankAccount.getAccountId())
                .account_type(bankAccount.getAccount_type())
                .balance(bankAccount.getBalance())
                .build();
    }
}
