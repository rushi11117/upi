package com.upi.transactionmanager.Utils;

import com.upi.transactionmanager.Entity.TransactionBuffer;

/**
 * The type String utils.
 */
public class StringUtils {

    /**
     * Generate message body string.
     *
     * @param transactionBuffer the transaction buffer
     * @param flag              the flag
     *
     * @return the string
     */
    public String generateMessageBody(TransactionBuffer transactionBuffer, String flag){
        String messageBody = "Dear Customer,\n" +
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
        return messageBody;
    }
}
