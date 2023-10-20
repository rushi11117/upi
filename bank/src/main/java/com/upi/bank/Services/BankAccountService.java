package com.upi.bank.Services;

import com.upi.bank.Repositories.BankAccountRepository;
import com.upi.bank.Entity.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * The type Bank account service.
 */
@Service
public class BankAccountService {

    @Autowired
    private static BankAccountRepository bankAccountRepository;

    /**
     * Instantiates a new Bank account service.
     *
     * @param bankAccountRepository the bank account repository
     */
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    /**
     * Create new bank account response entity.
     *
     * @param bankAccount the bank account
     *
     * @return the response entity
     */
    public ResponseEntity createNewBankAccount(BankAccount bankAccount) {
        return ResponseEntity.status(409).body(bankAccountRepository.save(bankAccount));
    }
}
