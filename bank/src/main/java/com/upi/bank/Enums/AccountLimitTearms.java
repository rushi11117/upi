package com.upi.bank.Enums;

/**
 * The enum Account limit tearms.
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
