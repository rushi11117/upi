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

/**
 * The type Transaction message raw.
 */
@Data
@Setter
@Getter
public class TransactionMessageRaw {

    private Long amount;

    private String senderUpiId;

    private String reciverUpiId;

    private String passwordHash;
}
