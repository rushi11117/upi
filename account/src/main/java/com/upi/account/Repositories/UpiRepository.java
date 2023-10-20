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

import com.upi.account.Entity.Upi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Upi repository.
 */
@Repository
public interface UpiRepository extends JpaRepository<Upi, Long> {

    /**
     * Find by upi id boolean.
     *
     * @param upiId the upi id
     *
     * @return the boolean
     */
    @Query("SELECT upi FROM Upi upi WHERE upi.upi_id = ?1")
    Upi findByUpiId(String upiId);
}
