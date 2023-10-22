package com.upi.bank.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * The type Bank customer.
 */
@Entity
@Data
@Table(name = "customers")
public class BankCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "first_name", columnDefinition = "varchar(255)")
    private String first_name;

    @Column(name = "last_name", columnDefinition = "varchar(255)")
    private String last_name;

    @Column(name = "email", columnDefinition = "varchar(255)")
    private String email;

    @Column(name = "phone_number", columnDefinition = "varchar(13)")
    private String phoneNumber;

    @Column(name = "date_of_birth" ,columnDefinition = "date")
    private Date date_of_birth;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "datetime")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", columnDefinition = "datetime")
    private Date updatedAt;
}

