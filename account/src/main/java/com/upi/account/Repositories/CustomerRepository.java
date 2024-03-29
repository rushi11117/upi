/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *      @since ${LAST_MODIFIED}
 *
 *     ----------------------------------------------------------------------------
 */


package com.upi.account.Repositories;

import com.upi.account.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Customer repository.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Find by email customer.
     *
     * @param customerEmail the customer email
     *
     * @return the customer
     */
    @Query("SELECT c FROM Customer c WHERE c.customer_email = ?1 ")
    Customer findByEmail(String customerEmail);


    /**
     * Update by email response entity.
     *
     * @param customer_email   the customer email
     * @param customer_name    the customer name
     * @param customer_address the customer address
     *
     * @return the response entity
     */
    @Modifying
    @Query("UPDATE Customer c SET c.customer_name = :customer_name, c.customer_address = :customer_address WHERE c.customer_email = :customer_email")
    int updateByEmail(@Param("customer_email") String customer_email, @Param("customer_name") String customer_name, @Param("customer_address") String customer_address);


    /**
     * Find by phone number customer.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the customer
     */
    @Query("SELECT c FROM Customer c WHERE c.phoneNumber = ?1")
    Customer findByPhoneNumber(String customerIdentifier);


    /**
     * Gets phone number of upi.
     *
     * @param customerUpi the customer upi
     *
     * @return the phone number of upi
     */
    @Query("SELECT c.phoneNumber FROM Customer c JOIN c.added_upi u WHERE u.upi_id = ?1")
    List<String> getPhoneNumberOfUpi(String customerUpi);
}
