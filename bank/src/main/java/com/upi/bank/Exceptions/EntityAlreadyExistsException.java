package com.upi.bank.Exceptions;

/**
 * The type Entity already exists exception.
 */
public class EntityAlreadyExistsException extends Exception{


    /**
     * Instantiates a new Entity already exists exception.
     *
     * @param value  the value
     * @param column the column
     */
    public EntityAlreadyExistsException(String value, String column) {
        super("USER WITH "+ column.toUpperCase() + " " + value.toUpperCase() +" ALREADY EXISTS");
    }
}
