
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * The type Transaction controller.
 */
@Controller
@RequestMapping("/transaction")
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
     * @param transactionMessageRaw the transaction message raw
     *
     * @return the transaction status
     */
//    @PutMapping("/upi/{fromUpi}/{toUpi}/{amount}/{passwordHash}")
    @PutMapping
    public ResponseEntity sendMoney(
            @RequestBody TransactionMessageRaw transactionMessageRaw
/*            @PathVariable String fromUpi,
            @PathVariable String toUpi,
            @PathVariable Long amount,
            @PathVariable String passwordHash*/
    ) {
        return ResponseEntity.ok().body(transactionUsingUpiService.sendMonyToSingleUpi(transactionMessageRaw));
    }
}
