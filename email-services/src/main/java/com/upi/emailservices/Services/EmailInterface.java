package com.upi.emailservices.Services;

import com.upi.emailservices.Entity.EmailDetails;

/**
 * The interface Email interface.
 */
public interface EmailInterface {
    /**
     * Send email string.
     *
     * @param emailDetails the email details
     *
     * @return the string
     */
    String sendEmail(EmailDetails emailDetails);
}
