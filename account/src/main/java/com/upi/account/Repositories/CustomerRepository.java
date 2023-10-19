/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *
 *     ----------------------------------------------------------------------------
 */

/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
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
}
