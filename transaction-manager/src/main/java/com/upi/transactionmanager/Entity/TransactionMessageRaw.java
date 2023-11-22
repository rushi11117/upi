/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *
 *     ----------------------------------------------------------------------------
 */

package com.upi.transactionmanager.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * The type Transaction message raw.
 */

@Component
@Data
@Setter
@Getter
public class TransactionMessageRaw {

    private Long amount;

    private String senderUpiId;

    private String reciverUpiId;

    private String passwordHash;
}