/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *      @since ${LAST_MODIFIED}
 *
 *     ----------------------------------------------------------------------------
 */

package com.upi.account.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

/**
 * The type Customer.
 */
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "upi_of_customer", cascade = CascadeType.ALL)
    private List<Upi> added_upi;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "customer_email")
    private String customer_email;

    @Column(name = "customer_phone_number")
    private String phoneNumber;

    @Column(name = "customer_date_of_birth")
    private Date dateOfBirth;

    @Column(name = "customer_address")
    private String customer_address;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}
