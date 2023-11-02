package com.upi.emailservices.ServicesInterfaces;

import com.upi.emailservices.Entity.EmailDetails;
import com.upi.emailservices.Services.EmailInterface;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * The type Email services.
 */
@Service
public class EmailServicesImpl  {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    /**
     * Instantiates a new Email services.
     *
     * @param javaMailSender the java mail sender
     */
    public EmailServicesImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    /**
     * Send email string.
     *
     * @return the string
     */
    public String sendEmail(EmailDetails emailDetails) {
        SimpleMailMessage simpleMailMessage
                = new SimpleMailMessage();
        try {
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(emailDetails.getEmail());
            simpleMailMessage.setText(emailDetails.getBody());
            simpleMailMessage.setSubject(emailDetails.getSubject());
            javaMailSender.send(simpleMailMessage);
            return "Mail Sent";
        } catch (Exception e) {
            System.out.println(e);
            return "Error Sending Email";
        }
    }
}
