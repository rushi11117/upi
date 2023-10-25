/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *
 *     ----------------------------------------------------------------------------
 */

package com.upi.transactionmessageservice.Enums;

/**
 * The enum Transaction status.
 */
public enum TransactionStatus {

    /**
     * Transaction failed transaction status.
     */
    TRANSACTION_FAILED(2),
    /**
     * Transaction success transaction status.
     */
    TRANSACTION_SUCCESS(1),
    /**
     * Transaction pending transaction status.
     */
    TRANSACTION_PENDING(3);

    private final int transactionCode;

    TransactionStatus(int transactionCode) {
        this.transactionCode = transactionCode;
    }

    /**
     * Gets transaction code.
     *
     * @return the transaction code
     */
    public int getTransactionCode() {
        return transactionCode;
    }
}
