package com.upi.smsservices.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.upi.smsservices.Entity.SMSDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.event.TreeWillExpandListener;

/**
 * The type Sms services.
 */
@Service
public class SMSServices {


    /**
     * Send sms response entity.
     *
     * @return the response entity
     */
    public ResponseEntity sendSMS(SMSDetails smsDetails) {

        Twilio.init("ACa0b92e651c1311b0a84c8e9ae6733a1d","a771b00263fb5c1b179f960d140c201a");
        Message.creator(new PhoneNumber("+19382385807"),
                new PhoneNumber("+917972504649"),
                "This is Test SMS '{$cluster'${spring.db.transaction.server.selected}'email_id}' '{messageQueue.bulk()}'"
        ).create();
        return null;
    }
}