package com.upi.bank.Repositories;

import com.upi.bank.Entity.BankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * The interface Bank customer repository.
 */
@Repository
public interface BankCustomerRepository extends JpaRepository<BankCustomer, Long> {

    /**
     * Find all by email bank customer.
     *
     * @param email the email
     *
     * @return the bank customer
     */
    @Query("SELECT customer FROM BankCustomer customer WHERE customer.email = ?1")
    BankCustomer findAllByEmail(String email);
}
