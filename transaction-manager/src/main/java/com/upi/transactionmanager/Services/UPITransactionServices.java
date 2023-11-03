package com.upi.transactionmanager.Services;

import com.upi.transactionmanager.Entity.TransactionBuffer;
import com.upi.transactionmanager.Entity.TransactionMessageRaw;
import com.upi.transactionmanager.Enums.ServerURIEnums;
import com.upi.transactionmanager.Enums.TransactionStatusEnum;
import com.upi.transactionmanager.Repositories.TransactionBufferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The type Upi transaction services.
 */
@Service
public class UPITransactionServices {


    /**
     * The Web client.
     */
    @Autowired
    private final WebClient webClient;

    @Autowired
    private TransactionBufferRepository transactionBufferRepository;

    @Autowired
    private EmailNotificationServices emailNotificationServices;

    @Autowired
    private SMSNotificationServices smsNotificationServices;


    /**
     * Instantiates a new Upi transaction services.
     *
     * @param webClient                   the web client
     * @param transactionBufferRepository the transaction buffer repository
     * @param emailNotificationServices   the notification services
     * @param smsNotificationServices     the sms notification services
     */
    public UPITransactionServices(
            WebClient webClient,
            TransactionBufferRepository transactionBufferRepository,
            EmailNotificationServices emailNotificationServices,
            SMSNotificationServices smsNotificationServices
    ) {
        this.webClient = webClient;
        this.transactionBufferRepository = transactionBufferRepository;
        this.emailNotificationServices = emailNotificationServices;
        this.smsNotificationServices = smsNotificationServices;
    }

    /**
     * Transfer money using transaction message raw transaction status enum.
     *
     * @param transactionMessageRaw the transaction message raw
     *
     * @return the transaction status enum
     */
    @Transactional
    public TransactionStatusEnum transferMoneyUsingTransactionMessageRaw(TransactionMessageRaw transactionMessageRaw) {

        AtomicReference<Boolean> transactionStatus = new AtomicReference<>(false);

        TransactionBuffer transactionBuffer = new TransactionBuffer();
        transactionBuffer.setSenderUpiId(transactionMessageRaw.getSenderUpiId());
        transactionBuffer.setReciverUpiId(transactionMessageRaw.getReciverUpiId());
        transactionBuffer.setAmmount(transactionMessageRaw.getAmount());
        Boolean credited = creditDebitAmmount(transactionMessageRaw.getReciverUpiId(), transactionMessageRaw.getAmount(), "CREDIT");
        Boolean debited = false;
        if (credited) {
            debited = creditDebitAmmount(transactionMessageRaw.getSenderUpiId(), transactionMessageRaw.getAmount(), "DEDUCT");
        }
        while (!debited) {
            debited = creditDebitAmmount(transactionMessageRaw.getReciverUpiId(), transactionMessageRaw.getAmount(), "DEDUCT");
        }

        if (credited && debited) {
            transactionBuffer.setTransactionStatus(TransactionStatusEnum.TRANSACTION_SUCCESS);
            transactionBuffer.setTransactionCompletion(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            initiateTransactionInTuple(transactionBuffer);
//            emailNotificationServices.initiateNotification(transactionBuffer);
//            smsNotificationServices.initiateNotification(transactionBuffer);
            return TransactionStatusEnum.TRANSACTION_SUCCESS;
        }
        transactionBuffer.setTransactionStatus(TransactionStatusEnum.TRANSACTION_FAILED);
        initiateTransactionInTuple(transactionBuffer);
        return TransactionStatusEnum.TRANSACTION_FAILED;

    }

    /**
     * Credit debit ammount boolean.
     *
     * @param reciverUpiId the reciver upi id
     * @param amount       the amount
     * @param flag         the flag
     *
     * @return the boolean
     */
    protected Boolean creditDebitAmmount(String reciverUpiId, Long amount, String flag) {
        ResponseEntity<Boolean> responseEntity = webClient.put()
                .uri(ServerURIEnums.BANK_SERVER_URI.getServerUri() + "/bankaccount/setbalance/{customerIdentifier}/{flag}/{ammount}", reciverUpiId, flag, amount)
                .retrieve()
                .toEntity(Boolean.class)
                .block();

        if (responseEntity != null) {
            HttpStatusCode responseCode = responseEntity.getStatusCode();
            if (responseCode.is5xxServerError() || responseCode.is4xxClientError()) {
                return false;
            } else {
                return responseEntity.getBody();
            }
        }
        return false;
    }


    /**
     * Initiate transaction in tuple transaction buffer.
     *
     * @param transactionBuffer the transaction buffer
     *
     * @return the transaction buffer
     */
    protected TransactionBuffer initiateTransactionInTuple(TransactionBuffer transactionBuffer) {
        return transactionBufferRepository.save(transactionBuffer);
    }


}
