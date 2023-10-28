package com.upi.transactionmanager.Repositories;

import com.upi.transactionmanager.Entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Test repository.
 */
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
