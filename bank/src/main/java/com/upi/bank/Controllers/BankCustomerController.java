package com.upi.bank.Controllers;

import com.upi.bank.Entity.BankCustomer;
import com.upi.bank.Services.BankCustomerServices;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Bank customer controller.
 */
@RestController
@RequestMapping("/bankcustomer")
public class BankCustomerController {

    private static BankCustomerServices bankCustomerServices;

    /**
     * Instantiates a new Bank customer controller.
     *
     * @param bankCustomerServices the bank customer services
     */
    public BankCustomerController(BankCustomerServices bankCustomerServices) {
        this.bankCustomerServices = bankCustomerServices;
    }

    /**
     * Create new bank account response entity.
     *
     * @param bankCustomer the bank customer
     *
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity createNewBankAccount(@RequestBody @NonNull BankCustomer bankCustomer) {
        if (bankCustomer.getEmail() == null) {
            return ResponseEntity.badRequest().body("Enter valid Email");
        } else if (bankCustomer.getPhoneNumber() == null) {
            return ResponseEntity.badRequest().body("Enter valid Phone Number");
        }
        return bankCustomerServices.createNewBankAccount(bankCustomer);
    }

}
