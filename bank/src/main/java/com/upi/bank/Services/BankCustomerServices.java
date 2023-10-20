package com.upi.bank.Services;

import com.upi.bank.Dto.DtoServices.BankCustomerDtoServices;
import com.upi.bank.Entity.BankCustomer;
import com.upi.bank.Repositories.BankCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * The type Bank customer services.
 */
@Service
public class BankCustomerServices {

    @Autowired
    private static BankCustomerRepository bankCustomerRepository;

    @Autowired
    private static BankCustomerDtoServices bankCustomerDtoServices;

    /**
     * Instantiates a new Bank customer services.
     *
     * @param bankCustomerRepository  the bank customer repository
     * @param bankCustomerDtoServices the bank customer dto services
     */
    public BankCustomerServices(BankCustomerRepository bankCustomerRepository, BankCustomerDtoServices bankCustomerDtoServices) {
        this.bankCustomerRepository = bankCustomerRepository;
        this.bankCustomerDtoServices = bankCustomerDtoServices;
    }

    /**
     * Create new bank account response entity.
     *
     * @param bankCustomer the bank customer
     *
     * @return the response entity
     */
    public ResponseEntity createNewBankAccount(BankCustomer bankCustomer) {
//        if (validateCustomer(bankCustomerDtoServices.mapBankCustomerToBankCustomerDto(bankCustomer)) != null) {
        if (validateCustomer(bankCustomer) == null) {
            return ResponseEntity.status(409).body(bankCustomerRepository.save(bankCustomer));
        }
        return ResponseEntity.badRequest().body("CUSTOMER ALREADY EXISTS");
    }

    /**
     * Validate customer bank customer.
     *
     * @param bankCustomerDto the bank customer dto
     *
     * @return the bank customer
     */
    public BankCustomer validateCustomer(BankCustomer bankCustomerDto) {
        return bankCustomerRepository.findAllByEmail(bankCustomerDto.getEmail());
    }
}
