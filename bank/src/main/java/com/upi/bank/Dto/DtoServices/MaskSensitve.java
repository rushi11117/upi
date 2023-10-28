package com.upi.bank.Dto.DtoServices;

/**
 * The type Mask sensitve.
 */
public class MaskSensitve {


    /**
     * Mask sensitive data string.
     *
     * @param securedEntity the secured entity
     *
     * @return the string
     */
    protected String maskSensitiveData(String securedEntity) {
        String[] splitedEmail = securedEntity.split("@");
        String tmpMask = splitedEmail[0].substring(splitedEmail[0].length() / 2);
        splitedEmail[0] = splitedEmail[0].replace(tmpMask, generateLengthMask(splitedEmail[0].length() / 2));
        if (splitedEmail.length == 1) {
            return splitedEmail[0];
        }
        return splitedEmail[0] +"@"+ splitedEmail[1];
    }

    private String generateLengthMask(int maskLength) {
        StringBuilder starString = new StringBuilder();
        for (int i = 0; i < maskLength; i++) {
            starString.append('*');
        }
        return starString.toString();
    }
}
