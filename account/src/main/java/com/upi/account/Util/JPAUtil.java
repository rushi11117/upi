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

package com.upi.account.Util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * The type Jpa util.
 */
public class JPAUtil {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("account-persister-unit");
    }

    /**
     * Gets entity manager factory.
     *
     * @return the entity manager factory
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }

}
