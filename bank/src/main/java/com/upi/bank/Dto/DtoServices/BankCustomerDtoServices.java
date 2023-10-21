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
        bankCustomer.setFirst_name(bankCustomerDto.getLast_name());
        bankCustomer.setLast_name(bankCustomerDto.getLast_name());
        bankCustomer.setEmail(bankCustomerDto.getEmail());
        bankCustomer.setPhoneNumber(bankCustomerDto.getPhoneNumber());
        bankCustomer.setDate_of_birth(bankCustomerDto.getDateOfBirth());
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
        MaskSensitve maskSensitve = new MaskSensitve();
        return BankCustomerDto.builder()
                .first_name(bankCustomer.getFirst_name())
                .last_name(bankCustomer.getLast_name())
                .email(maskSensitve.maskSensitiveData(bankCustomer.getEmail()))
                .phoneNumber(maskSensitve.maskSensitiveData(bankCustomer.getPhoneNumber()))
                .build();
    }
}
