package com.upi.bank.Repositories;

import com.upi.bank.Entity.BankAccount;
import com.upi.bank.Entity.BankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Bank account repository.
 */
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query("SELECT account FROM BankAccount account WHERE account.customer = :bankCustomer")
    List<BankAccount> getBankAccountByCustomerIdentifier(@Param("bankCustomer") BankCustomer bankCustomer);
}
