package com.upi.transactionmanager.Entity;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

/**
 * The type Email details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDetails {
    private String email;

    private String phoneNumber;

    private String body;

    private String subject;

    private String Signiture;
}
