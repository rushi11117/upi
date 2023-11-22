package com.upi.transactionmanager.Services;

import com.upi.transactionmanager.Entity.NotificationDetails;
import com.upi.transactionmanager.Entity.TransactionBuffer;
import com.upi.transactionmanager.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Sms notification services.
 */

@Service
public class SMSNotificationServices {

    @Autowired
    private StringUtils stringUtils;

    @Autowired
    private WebClientServices webClientServices;

    /**
     * Instantiates a new Sms notification services.
     *
     * @param stringUtils       the string utils
     * @param webClientServices the web client services
     */
    public SMSNotificationServices(
            StringUtils stringUtils,
            WebClientServices webClientServices
    ) {
        this.stringUtils = stringUtils;
        this.webClientServices = webClientServices;
    }

    /**
     * Initiate notification.
     *
     * @param transactionBuffer the transaction buffer
     *
     * @return the boolean
     */
    public Boolean initiateNotification(TransactionBuffer transactionBuffer) {
        NotificationDetails notificationDetails = new NotificationDetails();
        Boolean res = false;
        notificationDetails.setBody(stringUtils.generateMessageBody(transactionBuffer, "credited"));
        notificationDetails.setPhoneNumber(webClientServices.getPhoneNumberOfUpi(transactionBuffer.getReciverUpiId()));
        if (sendSMSNotification(notificationDetails)) {
            notificationDetails.setBody(stringUtils.generateMessageBody(transactionBuffer, "debited"));
            notificationDetails.setPhoneNumber(webClientServices.getPhoneNumberOfUpi(transactionBuffer.getSenderUpiId()));
            res = sendSMSNotification(notificationDetails);
        }
        return res;
    }


    /**
     * Send sms notification boolean.
     *
     * @param notificationDetails the notification details
     *
     * @return the boolean
     */
    private boolean sendSMSNotification(NotificationDetails notificationDetails) {
        return webClientServices.sendSMSNotification(notificationDetails);
    }
}
