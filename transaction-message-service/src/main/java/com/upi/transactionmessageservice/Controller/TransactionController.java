
/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *
 *     ----------------------------------------------------------------------------
 */

package com.upi.transactionmessageservice.Controller;

import com.upi.transactionmessageservice.Entity.TransactionMessageRaw;
import com.upi.transactionmessageservice.Enums.TransactionStatus;
import com.upi.transactionmessageservice.Services.TransactionUsingUpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Transaction controller.
 */
@Controller
@RestController("/transaction")
public class TransactionController {


    @Autowired
    private TransactionUsingUpiService transactionUsingUpiService;

    /**
     * Instantiates a new Transaction controller.
     *
     * @param transactionUsingUpiService the transaction using upi service
     */
    public TransactionController(TransactionUsingUpiService transactionUsingUpiService) {
        this.transactionUsingUpiService = transactionUsingUpiService;
    }

    /**
     * Send money transaction status.
     *
     * @param fromUpi      the from upi
     * @param toUpi        the to upi
     * @param amount       the amount
     * @param passwordHash the password hash
     *
     * @return the transaction status
     */
    @PutMapping("/upi/{fromUpi}/{toUpi}/{amount}")
    public TransactionStatus sendMoney(
//            @RequestBody TransactionMessageRaw transactionMessageRaw,
            @PathVariable String fromUpi,
            @PathVariable String toUpi,
            @PathVariable Long amount,
            @PathVariable String passwordHash
    ) {
        transactionUsingUpiService.sendMonyToSingleUpi(fromUpi ,toUpi ,amount, passwordHash);
        return TransactionStatus.TRANSACTION_FAILED;
    }
}
