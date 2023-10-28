package com.upi.transactionmanager.Services;

import com.upi.transactionmanager.Entity.TransactionMessageRaw;
import com.upi.transactionmanager.Enums.ServerURIEnums;
import com.upi.transactionmanager.Enums.TransactionStatusEnum;
import com.upi.transactionmanager.Exceptions.EndpointNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The type Upi transaction services.
 */
@Service
public class UPITransactionServices {


    @Autowired
    private final WebClient webClient;

    /**
     * Instantiates a new Upi transaction services.
     *
     * @param webClient the web client
     */
    public UPITransactionServices(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Transfer money using transaction message raw.
     *
     * @param transactionMessageRaw the transaction message raw
     *
     * @return the transaction status enum
     */
    @Transactional
    public TransactionStatusEnum transferMoneyUsingTransactionMessageRaw(TransactionMessageRaw transactionMessageRaw) {

        System.out.println(transactionMessageRaw);
        AtomicReference<Boolean> transactionStatus = new AtomicReference<>(true);


        webClient.put()
                .uri(ServerURIEnums.BANK_SERVER_URI.getServerUri()
                                + "/bankaccount/setbalance/{customerIdentifier}/{flag}/{ammount}",
                        transactionMessageRaw.getReciverUpiId(), "CREDIT", transactionMessageRaw.getAmount())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    return Mono.error(new EndpointNotFoundException(ServerURIEnums.BANK_SERVER_URI.getServerUri() + "/bankaccount/setbalance/" + transactionMessageRaw.getReciverUpiId().split("@")[0] + "@gmail.com" + "/" + "CREDIT" + "/" + transactionMessageRaw.getAmount()));
                })
                .toEntity(Boolean.class)
                .subscribe(transactionStatusMono -> {
                    transactionStatus.set(transactionStatusMono.getBody());
                });

        if (transactionStatus.get()) {
            System.out.println(transactionStatus.get().getClass());
            webClient.put()
                    .uri(ServerURIEnums.BANK_SERVER_URI.getServerUri()
                                    + "/bankaccount/setbalance/{customerIdentifier}/{flag}/{ammount}",
                            transactionMessageRaw.getSenderUpiId(), "DEDUCT", transactionMessageRaw.getAmount())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                        return Mono.error(new EndpointNotFoundException(ServerURIEnums.BANK_SERVER_URI.getServerUri() + "/bankaccount/setbalance/" + transactionMessageRaw.getReciverUpiId().split("@")[0] + "@gmail.com" + "/" + "CREDIT" + "/" + transactionMessageRaw.getAmount()));
                    })
                    .toEntity(Boolean.class)
                    .subscribe(transactionStatusMono -> {
                        transactionStatus.set(transactionStatusMono.getBody());
                    });
        }

        if (transactionStatus.get()) {
            return TransactionStatusEnum.TRANSACTION_SUCCESS;
        } else {
            return TransactionStatusEnum.TRANSACTION_FAILED;
        }
    }
}
