package com.upi.transactionmessageservice.Enums;/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *
 *     ----------------------------------------------------------------------------
 */

public enum AccountLimitTearms {
    /**
     * Min balance account limit tearms.
     */
    MIN_BALANCE(500.0);

    private final double value;

    AccountLimitTearms(double value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }
}
