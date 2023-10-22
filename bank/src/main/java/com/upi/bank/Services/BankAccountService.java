package com.upi.bank.Services;

import com.upi.bank.Dto.BankAccountDto;
import com.upi.bank.Dto.DtoServices.BankAccountDtoServices;
import com.upi.bank.Entity.BankAccount;
import com.upi.bank.Enums.AccountLimitTearms;
import com.upi.bank.Exceptions.MinimumAccountBalanceException;
import com.upi.bank.Repositories.BankAccountRepository;
import com.upi.bank.Repositories.BankCustomerRepository;
import com.upi.bank.Utils.ObjectCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * The type Bank account service.
 */
@Service
public class BankAccountService {

    @Autowired
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    private final BankCustomerRepository bankCustomerRepository;

    @Autowired
    private final BankAccountDtoServices bankAccountDtoServices;


    /**
     * Instantiates a new Bank account service.
     *
     * @param bankAccountRepository  the bank account repository
     * @param bankCustomerRepository the bank customer repository
     * @param bankAccountDtoServices the bank account dto services
     */
    public BankAccountService(BankAccountRepository bankAccountRepository, BankCustomerRepository bankCustomerRepository, BankAccountDtoServices bankAccountDtoServices) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankCustomerRepository = bankCustomerRepository;
        this.bankAccountDtoServices = bankAccountDtoServices;
    }

    /**
     * Create new bank account response entity.
     *
     * @param bankAccount   the bank account
     * @param customerEmail the customer email
     *
     * @return the response entity
     */
    public ResponseEntity createNewBankAccount(BankAccount bankAccount, String customerEmail) {
        System.out.println(bankAccount);
        bankAccount.setCustomer(bankCustomerRepository.findAllByColumn(customerEmail, "email"));
        return ResponseEntity.status(409).body(bankAccountRepository.save(bankAccount));
    }

    /**
     * Gets account by id.
     *
     * @param accountId the account id
     *
     * @return the account by id
     */
    public ResponseEntity getAccountById(Long accountId) {
        return ResponseEntity.ok().body(bankAccountRepository.findById(accountId));
    }

    /**
     * Gets all account details.
     *
     * @return the all account details
     */
    public List<BankAccountDto> getAllAccountDetails() {
        return bankAccountRepository.findAll().stream().map(bankAccount -> bankAccountDtoServices.mapBankAccountToBankAccountDto(bankAccount)).collect(Collectors.toList());
    }

    /**
     * Gets account balanca nb.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the account balanca nb
     * @throws MinimumAccountBalanceException the minimum account balance exception
     */
    public double getAccountBalanca(Object customerIdentifier) {
        AtomicReference<Double> balance = new AtomicReference<>(AccountLimitTearms.MIN_BALANCE.getValue());
        if (new ObjectCheck().isPhoneNumber(customerIdentifier)) {
            bankAccountRepository.findById(bankCustomerRepository.findAllByColumn(customerIdentifier.toString(), "phone_number").getCustomerId()).ifPresent(bankAccount -> {
                balance.set(bankAccount.getBalance());
            });
            return Double.parseDouble(balance.toString());
        }
        bankAccountRepository.findById(bankCustomerRepository.findAllByColumn(customerIdentifier.toString(), "email").getCustomerId()).ifPresent(
                bankAccount -> {
                    balance.set(bankAccount.getBalance());
                }
        );
        return Double.parseDouble(balance.toString());
    }
}
