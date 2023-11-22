package com.upi.bank.Exceptions;

import java.util.Date;

/**
 * The type Offline transaction session already running exception.
 */
public class OfflineTransactionSessionAlreadyRunningException extends Exception {

    /**
     * Instantiates a new Offline transaction session already running exception.
     *
     * @param customerIdentifier the customer identifier
     * @param lockedOn           the locked on
     */
    public OfflineTransactionSessionAlreadyRunningException(String customerIdentifier, Date lockedOn) {
        super("OFFLINE TRANSACTION SESSION OF USER" + customerIdentifier + " IS ALREADY BEGAN ON" + lockedOn);
    }
}
