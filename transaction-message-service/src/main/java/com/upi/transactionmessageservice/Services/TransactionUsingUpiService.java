/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *
 *     ----------------------------------------------------------------------------
 */

package com.upi.transactionmessageservice.Services;

import com.upi.transactionmessageservice.Entity.TransactionMessageRaw;
import com.upi.transactionmessageservice.Enums.AccountLimitTearms;
import com.upi.transactionmessageservice.Enums.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

/**
 * The type Transaction using upi service.
 */
@Service
public class TransactionUsingUpiService {

    @Autowired
    private final MicroservicesCommunicationServices microservicesCommunicationServices;

    /**
     * Instantiates a new Transaction using upi service.
     *
     * @param microservicesCommunicationServices the microservices communication services
     */
    public TransactionUsingUpiService(MicroservicesCommunicationServices microservicesCommunicationServices) {
        this.microservicesCommunicationServices = microservicesCommunicationServices;
    }

    /**
     * Send mony to single upi.
     *
     * @param transactionMessageRaw the transaction message raw
     *
     * @return the long
     */
    public TransactionStatus sendMonyToSingleUpi(TransactionMessageRaw transactionMessageRaw) {
        AtomicReference<Double> availableBalance = new AtomicReference<>(AccountLimitTearms.MIN_BALANCE.getValue());
        microservicesCommunicationServices.fetchAccountDetails(transactionMessageRaw.getSenderUpiId()).subscribe(balance -> {
            availableBalance.set(balance.getBody());
        });
        if (availableBalance.get() < transactionMessageRaw.getAmount()) {
            return TransactionStatus.TRANSACTION_FAILED_INSUFFICIENT_BALANCE;
        }

        TransactionStatus transactionStatus = microservicesCommunicationServices.initiateTransaction(transactionMessageRaw);
        if (transactionStatus == TransactionStatus.TRANSACTION_SUCCESS) {
            return TransactionStatus.TRANSACTION_SUCCESS;
        } else if (transactionStatus == TransactionStatus.TRANSACTION_PENDING) {
            return TransactionStatus.TRANSACTION_PENDING;
        }
        return TransactionStatus.TRANSACTION_PENDING;
    }
}
