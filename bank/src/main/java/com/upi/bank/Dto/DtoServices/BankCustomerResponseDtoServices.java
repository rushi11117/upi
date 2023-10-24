package com.upi.bank.Dto.DtoServices;

import com.upi.bank.Dto.BankCustomerDto;
import com.upi.bank.Dto.BankCustomerResponseDto;
import com.upi.bank.Entity.BankCustomer;
import org.springframework.stereotype.Service;

/**
 * The type Bank customer response dto services.
 */
@Service
public class BankCustomerResponseDtoServices {

    /**
     * The Mask sensitve.
     */
    MaskSensitve maskSensitve = new MaskSensitve();

    /**
     * Map to bank account response dto bank customer response dto.
     *
     * @param bankCustomer the bank customer
     *
     * @return the bank customer response dto
     */
    public BankCustomerResponseDto mapToBankCustomerToBankCustomerResponseDto(BankCustomer bankCustomer) {
        return BankCustomerResponseDto.builder()
                .first_name(bankCustomer.getFirst_name())
                .last_name(bankCustomer.getLast_name())
                .email(maskSensitve.maskSensitiveData(bankCustomer.getEmail()))
                .phoneNumber(maskSensitve.maskSensitiveData(bankCustomer.getPhoneNumber()))
                .build();
    }
}
