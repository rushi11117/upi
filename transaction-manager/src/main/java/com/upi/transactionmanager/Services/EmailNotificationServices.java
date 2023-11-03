package com.upi.transactionmanager.Services;

import com.upi.transactionmanager.Entity.NotificationDetails;
import com.upi.transactionmanager.Entity.TransactionBuffer;
import com.upi.transactionmanager.Enums.ServerURIEnums;
import com.upi.transactionmanager.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.atomic.AtomicReference;

/**
 * The type Notification services.
 */
@Service
public class EmailNotificationServices {

    @Autowired
    private WebClient webClient;

    @Autowired
    private NotificationDetails notificationDetails;

    @Autowired
    private StringUtils stringUtils;

    /**
     * Instantiates a new Notification services.
     *
     * @param webClient           the web client
     * @param notificationDetails the notification details
     * @param stringUtils         the string utils
     */
    public EmailNotificationServices(
            WebClient webClient,
            NotificationDetails notificationDetails,
            StringUtils stringUtils
    ) {
        this.webClient = webClient;
        this.notificationDetails = notificationDetails;
        this.stringUtils = stringUtils;
    }

    /**
     * Initiate notification.
     *
     * @param transactionBuffer the transaction buffer
     */
    public void initiateNotification(TransactionBuffer transactionBuffer) {
        NotificationDetails notificationDetails = new NotificationDetails();
        notificationDetails = generateEmailBody(transactionBuffer, notificationDetails, "credited");
        notificationDetails.setEmail(transactionBuffer.getReciverUpiId());
        notificationDetails.setSubject("Transaction With UPI");
        Boolean sent = sendEmailNotification(notificationDetails);
        if (sent == true) {
            notificationDetails = generateEmailBody(transactionBuffer, notificationDetails, "debited");
            notificationDetails.setEmail(transactionBuffer.getSenderUpiId());
            sent = sendEmailNotification(notificationDetails);
        }
        if (sent == true) {
            System.out.println("Email Notification Sent");
        }
    }

    /**
     * Generateemail details email details.
     *
     * @param transactionBuffer the transaction buffer
     *
     * @return the email details
     */
    private NotificationDetails generateEmailBody(TransactionBuffer transactionBuffer, NotificationDetails notificationDetails, String flag) {
        notificationDetails.setBody(stringUtils.generateMessageBody(transactionBuffer, flag));
        return notificationDetails;
    }

    /**
     * Send email notification boolean.
     *
     * @param notificationDetails the notification details
     *
     * @return the boolean
     */
    public Boolean sendEmailNotification(NotificationDetails notificationDetails) {
        AtomicReference<Boolean> res = new AtomicReference<>(false);
        webClient.put()
                .uri(ServerURIEnums.NOTIFICATION_SERVICE_URI.getServerUri() + "/email")
                .bodyValue(notificationDetails)
                .retrieve()
                .toEntity(Service.class)
                .subscribe(serviceResponseEntity -> {
                    res.set(serviceResponseEntity.equals("Mail Sent"));
                });
        return res.get();
    }


}
