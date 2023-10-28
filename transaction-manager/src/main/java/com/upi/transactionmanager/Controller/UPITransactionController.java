package com.upi.transactionmanager.Controller;

import com.upi.transactionmanager.Entity.TransactionMessageRaw;
import com.upi.transactionmanager.Services.UPITransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Upi transaction controller.
 */
@Controller
@RequestMapping("/transactions")
public class UPITransactionController {

    @Autowired
    private final UPITransactionServices upiTransactionServices;

    /**
     * Instantiates a new Upi transaction controller.
     *
     * @param upiTransactionServices the upi transaction services
     */
    public UPITransactionController(UPITransactionServices upiTransactionServices) {
        this.upiTransactionServices = upiTransactionServices;
    }

    /**
     * Transfer money using transaction message raw transaction status enum.
     *
     * @param transactionMessageRaw the transaction message raw
     *
     * @return the transaction status enum
     */
    @PutMapping
    public ResponseEntity transferMoneyUsingTransactionMessageRaw(@RequestBody TransactionMessageRaw transactionMessageRaw) {
        return ResponseEntity.ok().body(upiTransactionServices.transferMoneyUsingTransactionMessageRaw(transactionMessageRaw));
    }
}
