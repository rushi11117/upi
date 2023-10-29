package com.upi.transactionmanager.Exceptions;

/**
 * The type Endpoint not found exception.
 */
public class EndpointNotFoundException extends Exception{
    /**
     * Instantiates a new Endpoint not found exception.
     *
     * @param message the message
     */
    public EndpointNotFoundException(String message) {
        super(message);
    }
}
