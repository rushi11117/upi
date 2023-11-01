package com.upi.transactionmanager.Entity;

import com.upi.transactionmanager.Enums.TransactionStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * The type Transaction buffer.
 */
@Entity
@Data
@Table(name = "TransactionBuffer")
public class Transactions {

    @Id
    @Column(name = "transaction_id")
    private String id;

    @Column(name = "senders_upi")
    private String senderUpiId;

    @Column(name = "recivers_upi")
    private String reciverUpiId;

    @Column(name = "ammount")
    private Long ammount;

    @Column(name = "transaction_status")
    private TransactionStatusEnum transactionStatus;

    @Column(name = "transaction_begin")
    private Date transactionTime;

    @Column(name = "transaction_commit")
    private Date transactionCompletion;
}
