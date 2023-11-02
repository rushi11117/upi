package com.upi.smsservices.Controller;

import com.upi.smsservices.Entity.SMSDetails;
import com.upi.smsservices.Services.SMSServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Sms controller.
 */
@Controller
@RequestMapping("/sms")
public class SMSController {

    @Autowired
    private SMSServices smsServices;

    /**
     * Instantiates a new Sms controller.
     *
     * @param smsServices the sms services
     */
    public SMSController(SMSServices smsServices) {
        this.smsServices = smsServices;
    }

    /**
     * Send sms response entity.
     *
     * @return the response entity
     */

    @PutMapping
    public ResponseEntity sendSMS(SMSDetails smsDetails) {
        return smsServices.sendSMS(smsDetails);
    }
}
