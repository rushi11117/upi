package com.upi.bank.Services;

import com.upi.bank.Dto.BankCustomerResponseDto;
import com.upi.bank.Dto.DtoServices.BankCustomerResponseDtoServices;
import com.upi.bank.Dto.DtoServices.BankCustomerDtoServices;
import com.upi.bank.Entity.BankCustomer;
import com.upi.bank.Exceptions.EntityAlreadyExistsException;
import com.upi.bank.Repositories.BankCustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Bank customer services.
 */
@Service
public class BankCustomerServices {

    @Autowired
    private static BankCustomerRepository bankCustomerRepository;

    @Autowired
    private static BankCustomerDtoServices bankCustomerDtoServices;

    @Autowired
    private static BankCustomerResponseDtoServices bankCustomerResponseDtoServices;

    /**
     * Instantiates a new Bank customer services.
     *
     * @param bankCustomerRepository          the bank customer repository
     * @param bankCustomerDtoServices         the bank customer dto services
     * @param bankCustomerResponseDtoServices the bank account response dto services
     */
    public BankCustomerServices(BankCustomerRepository bankCustomerRepository, BankCustomerDtoServices bankCustomerDtoServices, BankCustomerResponseDtoServices bankCustomerResponseDtoServices) {
        this.bankCustomerRepository = bankCustomerRepository;
        this.bankCustomerDtoServices = bankCustomerDtoServices;
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
    public ResponseEntity createNewBankAccount(BankCustomer bankCustomer) throws EntityAlreadyExistsException {
        if (validateCustomer(bankCustomer.getEmail()) == null) {
            return ResponseEntity.status(409).body(bankCustomerRepository.save(bankCustomer));
        } else if (validateCustomer(bankCustomer.getPhoneNumber(), "phone_number") != null) {
            throw new EntityAlreadyExistsException(bankCustomer.getPhoneNumber(), "Phone Number");
        }
        throw new EntityAlreadyExistsException(bankCustomer.getEmail(), "EMAIL");
    }

    /**
     * Validate customer bank customer.
     *
     * @param values the values
     * @param column the column
     *
     * @return the bank customer
     */
    public BankCustomer validateCustomer(Object values, String... column) {
        if (column == null || column.length == 0) {
            return bankCustomerRepository.findAllByColumn(values.toString(), "email");
        }
        System.out.println(values.toString() + " " + column[0]);
        return bankCustomerRepository.findAllByColumn(values.toString(), column[0]);
    }

    /**
     * Update existing bank account response entity.
     *
     * @param bankCustomer the bank customer
     *
     * @return the response entity
     */
    @Transactional
    public ResponseEntity updateExistingBankAccount(BankCustomer bankCustomer) {
        bankCustomerRepository.updateBankCustomer(bankCustomer.getFirst_name(), bankCustomer.getLast_name(), bankCustomer.getDate_of_birth(), bankCustomer.getEmail());
        return ResponseEntity
                .ok("CUSTOMER UPDATED");
    }

    /**
     * Delete bank account response entity.
     *
     * @param bankCustomer the bank customer
     *
     * @return the response entity
     */
    public ResponseEntity deleteBankAccount(BankCustomer bankCustomer) {
        bankCustomerRepository.delete(bankCustomer);
        return ResponseEntity.ok().body("USER " + bankCustomer + " DELETED");
    }

    /**
     * Gets all bank account.
     *
     * @return the all bank account
     */
    public List<BankCustomerResponseDto> getAllBankAccounts() {
        return bankCustomerRepository.findAll()
                .stream()
                .map(bankCustomer -> bankCustomerResponseDtoServices.mapmapToBankCustomerToBankCustomerResponseDto(bankCustomer))
                .collect(Collectors.toList());
    }

    /**
     * Gets bank account.
     *
     * @param customerEmail the customer email
     *
     * @return the bank account
     */
    public BankCustomerResponseDto getBankAccount(String customerEmail) {
        return bankCustomerResponseDtoServices.mapmapToBankCustomerToBankCustomerResponseDto(bankCustomerRepository.findAllByColumn(customerEmail, "email"));
    }
}
