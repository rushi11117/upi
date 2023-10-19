
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

package com.upi.account.Exceptions;

/**
 * The type Upi already exists exception.
 */
public class UpiAlreadyExistsException extends Exception {
    /**
     * Instantiates a new Upi already exists exception.
     *
     * @param message the message
     */
    public UpiAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Upi already exists exception.
     */
    public UpiAlreadyExistsException() {
        super("Upi Already Generated.");
    }
}
