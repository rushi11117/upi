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
import com.upi.transactionmessageservice.Enums.ServerUris;
import com.upi.transactionmessageservice.Enums.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * The type Microservices communication services.
 */
@Service
public class MicroservicesCommunicationServices {

    @Autowired
    private final WebClient webClient;

    /**
     * Instantiates a new Microservices communication services.
     *
     * @param webClient the web client
     */
    public MicroservicesCommunicationServices(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Fetch account details object.
     *
     * @param upiId the upi id
     *
     * @return the object
     */
    public Mono<ResponseEntity<Double>> fetchAccountDetails(String upiId) {

        Mono<ResponseEntity<Double>> balanceMono = webClient.get()
                .uri(ServerUris.BANK_SERVER.getServerUri() + "bankaccount/getbalance/" + upiId.split("@")[0] + "@gmail.com")
                .retrieve()
                .toEntity(Double.class);

        return balanceMono;
    }

    /**
     * Initiate transaction transaction status.
     *
     * @param transactionMessageRaw the transaction message raw
     *
     * @return the transaction status
     */
    public TransactionStatus initiateTransaction(TransactionMessageRaw transactionMessageRaw) {

        webClient
        return TransactionStatus.TRANSACTION_SUCCESS;
    }
}
