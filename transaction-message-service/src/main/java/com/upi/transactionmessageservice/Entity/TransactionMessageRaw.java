/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *
 *     ----------------------------------------------------------------------------
 */

package com.upi.transactionmessageservice.Entity;

import lombok.Data;

/**
 * The type Transaction message raw.
 */
@Data
public class TransactionMessageRaw {

    private Long amount;

    private String senderUpiId;

    private String reciverUpiId;

}
