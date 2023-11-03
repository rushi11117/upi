package com.upi.smsservices.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.upi.smsservices.Entity.SMSDetails;
import com.upi.smsservices.Enums.SMSAPIEnums;
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
        Twilio.init(SMSAPIEnums.TWILLIO_ACCOUNT_SID.getValue(), SMSAPIEnums.TWILLIO_ACCOUNT_PASSWORD.getValue());
        Message.creator(new PhoneNumber(SMSAPIEnums.TWILLIO_PHONE_NUMBER.getValue()),
                new PhoneNumber(smsDetails.getPhoneNumber()),
                "This is Test SMS '{$cluster'${spring.db.transaction.server.selected}'email_id}' '{messageQueue.bulk()}'"+smsDetails.getBody()
        ).create();
        return null;
    }
}