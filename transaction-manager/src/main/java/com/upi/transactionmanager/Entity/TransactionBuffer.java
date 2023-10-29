package com.upi.transactionmanager.Entity;

import com.upi.transactionmanager.Enums.TransactionStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * The type Transaction buffer.
 */
@Entity
@Data
@Table(name = "TransactionBuffer")
public class TransactionBuffer {

    @Id
    @Column(name = "transaction_id")
    private String id;

    @PrePersist
    private void generateUniqueID() {
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String datetimePart = dateFormat.format(currentTime);
        String randomPart = generateRandomString(14 - datetimePart.length());
        this.id = datetimePart + randomPart;
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }


    @Column(name = "senders_upi")
    private String senderUpiId;

    @Column(name = "recivers_upi")
    private String reciverUpiId;

    @Column(name = "ammount")
    private Long ammount;

    @Column(name = "transaction_status")
    private TransactionStatusEnum transactionStatus;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transaction_begin")
    private Date transactionTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transaction_commit")
    private Date transactionCompletion;
}
