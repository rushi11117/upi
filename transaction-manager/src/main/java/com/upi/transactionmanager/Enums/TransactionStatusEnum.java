/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *
 *     ----------------------------------------------------------------------------
 */

package com.upi.transactionmanager.Enums;

/**
 * The enum Transaction status enum.
 */
public enum TransactionStatusEnum {

    /**
     * Transaction failed transaction status enum.
     */
    TRANSACTION_FAILED(2),
    /**
     * Transaction success transaction status enum.
     */
    TRANSACTION_SUCCESS(1),
    /**
     * Transaction pending transaction status enum.
     */
    TRANSACTION_PENDING(3),

    /**
     * Transaction failed insufficient balance transaction status enum.
     */
    TRANSACTION_FAILED_INSUFFICIENT_BALANCE(4);

    private final int transactionCode;

    TransactionStatusEnum(int transactionCode) {
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
