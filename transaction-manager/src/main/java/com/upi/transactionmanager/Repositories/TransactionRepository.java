package com.upi.transactionmanager.Repositories;

import com.upi.transactionmanager.Entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Transaction repository.
 */
public interface TransactionRepository extends JpaRepository<Transactions, String> {
}
