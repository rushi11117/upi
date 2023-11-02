package com.upi.emailservices.Controller;

import com.upi.emailservices.Entity.EmailDetails;
import com.upi.emailservices.ServicesInterfaces.EmailServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Email controller.
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailServicesImpl emailServices;

    /**
     * Instantiates a new Email controller.
     *
     * @param emailServices the email services
     */
    public EmailController(EmailServicesImpl emailServices) {
        this.emailServices = emailServices;
    }

    /**
     * Send email string.
     *
     * @return the string
     */
    @PutMapping
    public ResponseEntity sendEmail(EmailDetails emailDetails) {
        String res = emailServices.sendEmail(emailDetails);
        System.out.println(res);
        return ResponseEntity.ok().body(res);
    }
}
