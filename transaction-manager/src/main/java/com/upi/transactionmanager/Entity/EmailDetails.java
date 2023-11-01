package com.upi.transactionmanager.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Email details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {
    private String email;

    private String body;

    private String subject;

    private String Signiture;
}
