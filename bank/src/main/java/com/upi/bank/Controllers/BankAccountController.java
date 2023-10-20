package com.upi.bank.Controllers;

import com.upi.bank.Entity.BankAccount;
import com.upi.bank.Services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Bank account controller.
 */
@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {

    @Autowired
    private static BankAccountService bankAccountService;


    /**
     * Instantiates a new Bank account controller.
     *
     * @param bankAccountService the bank account service
     */
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    /**
     * Create new bank account response entity.
     *
     * @param bankAccount the bank account
     *
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity createNewBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.createNewBankAccount(bankAccount);
    }
}
