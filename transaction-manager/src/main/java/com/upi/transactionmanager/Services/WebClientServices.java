package com.upi.transactionmanager.Services;

import com.upi.transactionmanager.Entity.NotificationDetails;
import com.upi.transactionmanager.Enums.ServerURIEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * The type Web client services.
 */
@Service
public class WebClientServices {

    @Autowired
    private WebClient webClient;

    /**
     * Instantiates a new Web client services.
     *
     * @param webClient the web client
     */
    public WebClientServices(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Gets phone number of upi.
     *
     * @param customerUpi the customer upi
     *
     * @return the phone number of upi
     */
    public String getPhoneNumberOfUpi(String customerUpi) {
        return webClient.get()
                .uri(ServerURIEnums.UPI_SERVER_URI.getServerUri() + "/customer/phonenumber/{customerUpi}", customerUpi)
                .retrieve()
                .toEntity(String.class)
                .toString();
    }

    public Boolean sendSMSNotification(NotificationDetails notificationDetails) {
        ResponseEntity res = webClient.put()
                .uri(ServerURIEnums.SMS_NOTIFICATION_SERVICE_USRI.getServerUri() + "/sms")
                .body(BodyInserters.fromValue(notificationDetails))
                .retrieve()
                .toEntity(ResponseEntity.class)
                .block();
        return res != null;
    }
}
