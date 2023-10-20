package com.upi.bank.Dto.DtoServices;

import com.upi.bank.Dto.BankCustomerDto;
import com.upi.bank.Entity.BankCustomer;
import lombok.Builder;
import org.springframework.stereotype.Service;

/**
 * The type Bank customer dto services.
 */
@Service
@Builder
public class BankCustomerDtoServices {

//    BankCustomer bankCustomer;

    /**
     * Map bank customer dto to bank customer bank customer.
     *
     * @param bankCustomerDto the bank customer dto
     *
     * @return the bank customer
     */
    public BankCustomer mapBankCustomerDtoToBankCustomer(BankCustomerDto bankCustomerDto) {
        BankCustomer bankCustomer = new BankCustomer();
        bankCustomer.setFirstName(bankCustomerDto.getFirstName());
        bankCustomer.setLastName(bankCustomerDto.getLastName());
        bankCustomer.setEmail(bankCustomerDto.getEmail());
        bankCustomer.setPhoneNumber(bankCustomerDto.getPhoneNumber());
        bankCustomer.setDateOfBirth(bankCustomerDto.getDateOfBirth());
        return bankCustomer;
    }

    /**
     * Map bank customer to bank customer dto bank customer dto.
     *
     * @param bankCustomer the bank customer
     *
     * @return the bank customer dto
     */
    public BankCustomerDto mapBankCustomerToBankCustomerDto(BankCustomer bankCustomer) {
        return BankCustomerDto.builder()
                .firstName(bankCustomer.getFirstName())
                .lastName(bankCustomer.getLastName())
                .email(maskSensitiveData(bankCustomer.getEmail()))
                .phoneNumber(maskSensitiveData(bankCustomer.getPhoneNumber()))
                .build();
    }

    /**
     * Create secured mask for sensitive entity.
     *
     * @param securedEntity the bank customer
     *
     * @return the secured string mask entity
     */
    private String maskSensitiveData(String securedEntity) {
        String[] splitedEmail = securedEntity.split("@");
        String tmpMask = splitedEmail[0].substring(splitedEmail[0].length() / 2);
        splitedEmail[0] = splitedEmail[0].replace(tmpMask, generateLengthMask(splitedEmail[0].length()/2));
        return splitedEmail[0] + splitedEmail[1];
    }

    private String generateLengthMask(int maskLength) {
        StringBuilder starString = new StringBuilder();
        for (int i = 0; i < maskLength; i++) {
            starString.append('*');
        }
        return starString.toString();
    }
}
