package com.upi.bank.Services;

import com.upi.bank.Dto.BankAccountDto;
import com.upi.bank.Dto.DtoServices.BankAccountDtoServices;
import com.upi.bank.Entity.BankAccount;
import com.upi.bank.Enums.AccountLimitTearms;
import com.upi.bank.Enums.AccountLockStatus;
import com.upi.bank.Enums.ServerUri;
import com.upi.bank.Exceptions.OfflineTransactionSessionAlreadyRunningException;
import com.upi.bank.Repositories.BankAccountRepository;
import com.upi.bank.Repositories.BankCustomerRepository;
import com.upi.bank.Utils.ObjectCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

    @Autowired
    private final BankCustomerServices bankCustomerServices;

    @Autowired
    private final WebClient webClient;


    /**
     * Instantiates a new Bank account service.
     *
     * @param bankAccountRepository  the bank account repository
     * @param bankCustomerRepository the bank customer repository
     * @param bankAccountDtoServices the bank account dto services
     * @param webClient              the web client
     * @param bankCustomerServices   the bank customer services
     */
    public BankAccountService(
            BankAccountRepository bankAccountRepository,
            BankCustomerRepository bankCustomerRepository,
            BankAccountDtoServices bankAccountDtoServices,
            WebClient webClient,
            BankCustomerServices bankCustomerServices
            ) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankCustomerRepository = bankCustomerRepository;
        this.bankAccountDtoServices = bankAccountDtoServices;
        this.webClient = webClient;
        this.bankCustomerServices = bankCustomerServices;
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
        if (bankAccount.getBalance() == 0) {
            bankAccount.setBalance(AccountLimitTearms.MIN_BALANCE.getValue());
        }
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
     * Gets account balance.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the account balance
     */
    public double getAccountBalance(Object customerIdentifier) {
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

    /**
     * Generate upi for bank account mono.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the mono
     */
    public Mono<ResponseEntity<String>> generateUpiForBankAccount(String customerIdentifier) {
        return webClient.get()
                .uri(ServerUri.UPI_SERVER.getServiceurl() + "/upi/new/" + customerIdentifier)
                .retrieve()
                .toEntity(String.class);
    }

    /**
     * Sets account balance nb.
     *
     * @param customerIdentifier the customer identifier
     * @param ammount            the ammount
     * @param flag               the flag
     *
     * @return the account balance nb
     */
    public boolean setAccountBalanceNB(String customerIdentifier, Long ammount, String flag) {
        System.out.println(customerIdentifier);
        List<BankAccount> bankAccount = bankAccountRepository.getBankAccountByCustomerIdentifier(bankCustomerRepository.findAllByColumn(customerIdentifier, "email"));
        BankAccount reciversBankAccount;
        if (flag.equals("DEDUCT")) {
            bankAccount.get(0).setBalance(bankAccount.get(0).getBalance() - ammount);
            reciversBankAccount = bankAccount.get(0);
            bankAccountRepository.save(reciversBankAccount);
            return true;
        } else if (flag.equals("CREDIT")) {
            bankAccount.get(0).setBalance(bankAccount.get(0).getBalance() + ammount);
            reciversBankAccount = bankAccount.get(0);
            bankAccountRepository.save(reciversBankAccount);
            return true;
        }
        return false;
    }

    /**
     * Lock banktransactions boolean.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the boolean
     * @throws OfflineTransactionSessionAlreadyRunningException the offline transaction session already running exception
     */
    public Boolean lockBanktransactions(String customerIdentifier) throws OfflineTransactionSessionAlreadyRunningException {
        System.out.println("lockBanktransactionsServices");
        List<BankAccount> bankAccount = bankAccountRepository.getBankAccountByCustomerIdentifier(bankCustomerRepository.findAllByColumn(customerIdentifier, "email"));
        if (bankAccount.get(0).getLockflag() == AccountLockStatus.LOCKED) {
            throw new OfflineTransactionSessionAlreadyRunningException(customerIdentifier, bankAccount.get(0).getUpdatedAt());
        }
        if (bankAccount.get(0) == null) {
            return false;
        }

        bankAccount.get(0).setLockflag(AccountLockStatus.LOCKED);
        bankAccountRepository.save(bankAccount.get(0));
        return true;

//        else {
//        bankAccount.get(0).setLockflag(AccountLockStatus.LOCKED);
//        bankAccountRepository.save(bankAccount.get(0));
//        return true;
//        }
    }

    /**
     * Unlock banktransactions boolean.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the boolean
     */
    public Boolean unlockBanktransactions(String customerIdentifier) {
        System.out.println("lockBanktransactionsServices");
        List<BankAccount> bankAccount = bankAccountRepository.getBankAccountByCustomerIdentifier(bankCustomerRepository.findAllByColumn(customerIdentifier, "email"));
        if (bankAccount.get(0) == null) {
            return false;
        } else {
            if (setAccountBalanceNB(customerIdentifier, getAmmountFromWallet(customerIdentifier), "CREDIT"))
                bankAccount.get(0).setLockflag(AccountLockStatus.UNLOCKED);
            bankAccountRepository.save(bankAccount.get(0));
            return true;
        }
    }

    /**
     * Gets ammount from wallet.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the ammount from wallet
     */
    public Long getAmmountFromWallet(String customerIdentifier) {
        return 500L;
    }

    /**
     * Gets flag status.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the flag status
     */
    public AccountLockStatus getlockFlagStatus(String customerIdentifier) {
        return bankAccountRepository.findById(bankCustomerRepository.findAllByColumn(customerIdentifier, "email").getCustomerId()).get().getLockflag();
    }
}
