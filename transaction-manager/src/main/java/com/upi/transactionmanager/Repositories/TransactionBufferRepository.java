package com.upi.transactionmanager.Repositories;

import com.upi.transactionmanager.Entity.TransactionBuffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Transaction buffer repository.
 */
@Repository("TransactionBufferRepository")
public interface TransactionBufferRepository extends JpaRepository<TransactionBuffer, String> {
}
