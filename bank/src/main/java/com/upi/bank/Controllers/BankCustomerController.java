package com.upi.bank.Controllers;

import com.upi.bank.Dto.BankCustomerResponseDto;
import com.upi.bank.Dto.DtoServices.BankCustomerDtoServices;
import com.upi.bank.Dto.DtoServices.BankCustomerResponseDtoServices;
import com.upi.bank.Entity.BankCustomer;
import com.upi.bank.Exceptions.EntityAlreadyExistsException;
import com.upi.bank.Services.BankAccountService;
import com.upi.bank.Services.BankCustomerServices;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Bank customer controller.
 */
@RestController
@RequestMapping("/bankcustomer")
public class BankCustomerController {

    private static BankCustomerServices bankCustomerServices;
    private static BankAccountService bankAccountService;
    private BankCustomerResponseDtoServices bankCustomerResponseDtoServices;

    /**
     * Instantiates a new Bank customer controller.
     *
     * @param bankCustomerServices    the bank customer services
     * @param bankAccountService      the bank account service
     * @param bankCustomerDtoServices the bank customer dto services
     */
    public BankCustomerController(
            BankCustomerServices bankCustomerServices,
            BankAccountService bankAccountService,
            BankCustomerDtoServices bankCustomerDtoServices
    ) {
        this.bankAccountService = bankAccountService;
        this.bankCustomerServices = bankCustomerServices;
        this.bankCustomerResponseDtoServices = bankCustomerResponseDtoServices;
    }

    /**
     * Create new bank account response entity.
     *
     * @param bankCustomer the bank customer
     *
     * @return the response entity
     * @throws EntityAlreadyExistsException the entity already exists exception
     */
    @PostMapping
    public ResponseEntity createNewBankAccount(@RequestBody @NonNull BankCustomer bankCustomer) throws EntityAlreadyExistsException {
        if (bankCustomer.getEmail() == null) {
            return ResponseEntity.badRequest().body("Enter valid Email");
        } else if (bankCustomer.getPhoneNumber() == null) {
            return ResponseEntity.badRequest().body("Enter valid Phone Number");
        }
        return bankCustomerServices.createNewBankAccount(bankCustomer);
    }

    /**
     * Add buld data customer list.
     *
     * @param bankCustomers the bank customers
     *
     * @return the list
     * @throws EntityAlreadyExistsException the entity already exists exception
     */
    @PostMapping("/bulk")
    public List<ResponseEntity> addBuldDataCustomer(@RequestBody @NonNull List<BankCustomer> bankCustomers) throws EntityAlreadyExistsException {
        return bankCustomers.stream()
                .map(bankCustomer -> {
                    try {
                        return createNewBankAccount(bankCustomer);
                    } catch (EntityAlreadyExistsException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * Update existing bank account response entity.
     *
     * @param bankCustomer the bank customer
     *
     * @return the response entity
     */
    @PutMapping
    public ResponseEntity updateExistingBankAccount(@RequestBody BankCustomer bankCustomer) {
        return bankCustomerServices.updateExistingBankAccount(bankCustomer);
    }

    /**
     * Delete bank account response entity.
     *
     * @param bankCustomer the bank customer
     *
     * @return the response entity
     */
    @DeleteMapping
    public ResponseEntity deleteBankAccount(@RequestBody BankCustomer bankCustomer) {

        return bankCustomerServices.deleteBankAccount(bankCustomer);
    }

    /**
     * Gets bank account.
     *
     * @param customerEmail the customer email
     *
     * @return the bank account
     */
    @GetMapping("/{customerEmail}")
    public BankCustomerResponseDto getBankAccount(@PathVariable(name = "customerEmail") String customerEmail) {
        return bankCustomerServices.getBankAccount(customerEmail);
    }

    /**
     * Gets all bank accounts.
     *
     * @return the all bank accounts
     */
    @GetMapping("/all")
    public List<BankCustomerResponseDto> getAllBankAccounts() {
        return bankCustomerServices.getAllBankAccounts();
    }

    /**
     * Gets my upi.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the my upi
     */
    @GetMapping("/myupi/{customerIdentifier}")
    public Mono<ResponseEntity<String>> getMyUpi(@PathVariable String customerIdentifier) {
        return bankAccountService.generateUpiForBankAccount(customerIdentifier);
    }

}
