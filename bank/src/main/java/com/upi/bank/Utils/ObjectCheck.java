package com.upi.bank.Utils;

/**
 * The type Object check.
 */
public class ObjectCheck {

    /**
     * Is phone number boolean.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the boolean
     */
    public boolean isPhoneNumber(Object customerIdentifier) {
        try {
            Long.parseLong(customerIdentifier.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
