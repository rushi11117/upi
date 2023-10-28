package com.upi.bank.Repositories;

import com.upi.bank.Entity.BankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


/**
 * The interface Bank customer repository.
 */
@Repository
public interface BankCustomerRepository extends JpaRepository<BankCustomer, Long> {

    /**
     * Find all by column bank customer.
     *
     * @param value  the value
     * @param column the column
     *
     * @return the bank customer
     */
    @Query("SELECT customer FROM BankCustomer customer WHERE " +
            "CASE " +
            "WHEN :column = 'email' THEN customer.email = :value " +
            "WHEN :column = 'phone_number' THEN customer.phoneNumber = :value " +
            "ELSE true " +
            "END = true")
    BankCustomer findAllByColumn(@Param("value") String value, @Param("column") String column);

    /**
     * Update bank customer.
     *
     * @param firstName     the first name
     * @param lastName      the last name
     * @param dateOfBirth   the date of birth
     * @param customerEmail the customer email
     */
    @Modifying
    @Query("UPDATE BankCustomer customer " +
            "SET customer.first_name = :firstName, customer.last_name = :lastName, customer.date_of_birth = :dateOfBirth " +
            "WHERE customer.email = :customerEmail")
    void updateBankCustomer(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("dateOfBirth") Date dateOfBirth, @Param("customerEmail") String customerEmail);


}
