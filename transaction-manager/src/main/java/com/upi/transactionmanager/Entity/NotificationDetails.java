package com.upi.transactionmanager.Entity;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * The type Email details.
 */

@Component
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
