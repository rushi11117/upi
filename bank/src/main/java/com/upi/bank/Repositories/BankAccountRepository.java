package com.upi.bank.Repositories;

import com.upi.bank.Entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Bank account repository.
 */
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount ,Long> {
}
