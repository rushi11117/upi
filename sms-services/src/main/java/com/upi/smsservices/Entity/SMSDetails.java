package com.upi.smsservices.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Email details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMSDetails {
    private String phoneNumber;

    private String body;

    private String title;

    private String digitalSigniture;
}
