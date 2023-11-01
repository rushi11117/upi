package com.upi.transactionmanager.Services;

import com.upi.transactionmanager.Entity.EmailDetails;
import com.upi.transactionmanager.Entity.TransactionBuffer;
import com.upi.transactionmanager.Enums.ServerURIEnums;
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

    /**
     * Instantiates a new Notification services.
     *
     * @param webClient    the web client
     * @param emailDetails the email details
     */
    public EmailNotificationServices(WebClient webClient, EmailDetails emailDetails) {
        this.webClient = webClient;
        this.emailDetails = emailDetails;
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
        String emailBody = "Dear Customer,\n" +
                "\n" +
                "Your A/c No xx 7043 " + flag + " by INR" + Double.parseDouble(transactionBuffer.getAmmount().toString()) + "on " + transactionBuffer.getTransactionCompletion() + " with UPI RRN " + transactionBuffer.getId() + ". A/c Bal is INR 507.81 CR and AVL Bal is INR 507.81 CR-MAHABANK\n" +
                "\n" +
                "Yours Faithfully,\n" +
                "Bank Of Maharashtra\n" +
                "\n" +
                "\n" +
                "\n" +
                "Please use our toll free numbers 1800 233 4526 and 1800 102 2636 for any banking related queries.\n" +
                "Connect with us on Facebook, Twitter, LinkedIn @mahabank\n" +
                "\n" +
                "Disclaimer:\n" +
                "\n" +
                "This e-mail may contain Privileged/Confidential information and is intended only for the individual(s) named. Please notify the sender, if you have received this e-mail by mistake and delete it from your system. Information in this message that do not relate to the official business of the company shall be understood as neither given nor endorsed by it. E-mail transmission cannot be guaranteed to be secure or error-free. The sender does not accept liability for any errors or omissions in the contents of this message which arise as a result of e-mail transmission. If verification is required please request a hard-copy version.";
        emailDetails.setBody(emailBody);
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
