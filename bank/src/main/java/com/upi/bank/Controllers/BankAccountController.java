package com.upi.bank.Controllers;

import com.upi.bank.Dto.BankAccountDto;
import com.upi.bank.Entity.BankAccount;
import com.upi.bank.Exceptions.OfflineTransactionSessionAlreadyRunningException;
import com.upi.bank.Services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param bankAccount   the bank account
     * @param customerEmail the customer email
     *
     * @return the response entity
     */
    @PostMapping("/{customerEmail}")
    public ResponseEntity createNewBankAccount(@RequestBody BankAccount bankAccount, @PathVariable String customerEmail) {
        return bankAccountService.createNewBankAccount(bankAccount, customerEmail);
    }

    /**
     * Gets account by id.
     *
     * @param accountId the account id
     *
     * @return the account by id
     */
    @GetMapping("/{accountId}")
    public ResponseEntity getAccountById(@PathVariable Long accountId) {
        return bankAccountService.getAccountById(accountId);
    }

    /**
     * Gets all account details.
     *
     * @return the all account details
     */
    @GetMapping("/all")
    public List<BankAccountDto> getAllAccountDetails() {
        return bankAccountService.getAllAccountDetails();
    }

    /**
     * Gets account balance nb.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the account balance nb
     */
    @GetMapping("/getbalance/{customerIdentifier}")
    public ResponseEntity getAccountBalanceNB(@PathVariable Object customerIdentifier) {
        System.out.println(customerIdentifier.toString());
        return ResponseEntity.ok().body(bankAccountService.getAccountBalance(customerIdentifier.toString()));
    }

    /**
     * Sets account balance nb.
     *
     * @param customerIdentifier the customer identifier
     * @param flag               the flag
     * @param ammount            the ammount
     *
     * @return the account balance nb
     */
    @PutMapping("/setbalance/{customerIdentifier}/{flag}/{ammount}")
    public ResponseEntity setAccountBalanceNB(@PathVariable String customerIdentifier, @PathVariable String flag, @PathVariable Long ammount) {
        System.out.println("ECES");
        Boolean res = bankAccountService.setAccountBalanceNB(customerIdentifier, ammount, flag);
        return ResponseEntity.ok().body(res);
    }

    /**
     * Lock banktransactions response entity.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the response entity
     * @throws OfflineTransactionSessionAlreadyRunningException the offline transaction session already running exception
     */
    @PutMapping("/lockbanktransactions/{customerIdentifier}")
    public ResponseEntity lockBanktransactions(@PathVariable String customerIdentifier) throws OfflineTransactionSessionAlreadyRunningException {
        System.out.println("lockBanktransactionsController");
        return ResponseEntity.ok().body(bankAccountService.lockBanktransactions(customerIdentifier));
    }

    /**
     * Unlock banktransactions response entity.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the response entity
     */
    @PutMapping("/unlockbanktransactions/{customerIdentifier}")
    public ResponseEntity unlockBanktransactions(@PathVariable String customerIdentifier) {
        System.out.println("lockBanktransactionsController");
        return ResponseEntity.ok().body(bankAccountService.unlockBanktransactions(customerIdentifier));
    }

    /**
     * Instantiates a new Getlock flag status.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the flag status
     */
    @GetMapping("/getlockflagstatus/{customerIdentifier}")
    public ResponseEntity getlockFlagStatus(@PathVariable String customerIdentifier) {
        return ResponseEntity.ok().body(bankAccountService.getlockFlagStatus(customerIdentifier));
    }

    /**
     * Test mapping string.
     *
     * @return the string
     */
    @PutMapping("/test")
    public String testMapping() {
        System.out.println("Hello");
        return "Hello";
    }
}
