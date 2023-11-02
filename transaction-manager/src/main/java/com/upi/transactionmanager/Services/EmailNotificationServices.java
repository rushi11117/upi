package com.upi.transactionmanager.Services;

import com.upi.transactionmanager.Entity.EmailDetails;
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
    private EmailDetails emailDetails;

    @Autowired
    private StringUtils stringUtils;

    /**
     * Instantiates a new Notification services.
     *
     * @param webClient    the web client
     * @param emailDetails the email details
     * @param stringUtils  the string utils
     */
    public EmailNotificationServices(
            WebClient webClient,
            EmailDetails emailDetails,
            StringUtils stringUtils
    ) {
        this.webClient = webClient;
        this.emailDetails = emailDetails;
        this.stringUtils = stringUtils;
    }

    /**
     * Initiate notification.
     *
     * @param transactionBuffer the transaction buffer
     */
    public void initiateNotification(TransactionBuffer transactionBuffer) {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails = generateEmailBody(transactionBuffer, emailDetails, "credited");
        emailDetails.setEmail(transactionBuffer.getReciverUpiId());
        emailDetails.setSubject("Transaction With UPI");
        Boolean sent = sendEmailNotification(emailDetails);
        if (sent == true) {
            emailDetails = generateEmailBody(transactionBuffer, emailDetails, "debited");
            emailDetails.setEmail(transactionBuffer.getSenderUpiId());
            sent = sendEmailNotification(emailDetails);
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
    private EmailDetails generateEmailBody(TransactionBuffer transactionBuffer, EmailDetails emailDetails, String flag) {
        emailDetails.setBody(stringUtils.generateMessageBody(transactionBuffer, flag));
        return emailDetails;
    }

    /**
     * Send email notification boolean.
     *
     * @param emailDetails the email details
     *
     * @return the boolean
     */
    public Boolean sendEmailNotification(EmailDetails emailDetails) {
        AtomicReference<Boolean> res = new AtomicReference<>(false);
        webClient.put()
                .uri(ServerURIEnums.NOTIFICATION_SERVICE_URI.getServerUri() + "/email")
                .bodyValue(emailDetails)
                .retrieve()
                .toEntity(Service.class)
                .subscribe(serviceResponseEntity -> {
                    res.set(serviceResponseEntity.equals("Mail Sent"));
                });
        return res.get();
    }


}
