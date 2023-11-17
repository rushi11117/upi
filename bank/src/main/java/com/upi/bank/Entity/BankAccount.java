package com.upi.bank.Entity;

import com.upi.bank.Enums.AccountLockStatus;
import com.upi.bank.Enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * The type Bank account.
 */
@Entity
@Data
@Table(name = "accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "customer_id", columnDefinition = "bigint(20)")
    private BankCustomer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", columnDefinition = "enum('CHECKING', 'LOAN', 'SAVING')")
    private AccountType account_type;

    @Column(name = "balance" ,columnDefinition = "double")
    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "lockflag" ,columnDefinition = "enum('UNLOCKED', 'LOCKED') default 'UNLOCKED'")
    private AccountLockStatus lockflag = AccountLockStatus.UNLOCKED;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at" , columnDefinition = "datetime")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", updatable = false ,columnDefinition = "datetime")
    private Date updatedAt;
}

