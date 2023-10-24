package com.upi.account.Util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * The type Object check.
 */

@Service
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
