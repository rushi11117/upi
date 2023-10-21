/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *      @since ${LAST_MODIFIED}
 *
 *     ----------------------------------------------------------------------------
 */


package com.upi.account.dto.DtoServices;

import com.upi.account.Entity.Customer;
import com.upi.account.dto.CustomerRequestDto;
import com.upi.account.dto.CustomerResponseDto;
import org.springframework.stereotype.Service;

/**
 * The type Customer response services.
 */
@Service
public class CustomerResponseDtoServices {

    /**
     * Map to customer response dto customer response dto.
     *
     * @param customer the customer
     *
     * @return the customer response dto
     */
    public CustomerResponseDto mapToCustomerResponseDto(Customer customer) {
        return CustomerResponseDto.builder()
                .customer_address(customer.getCustomer_address())
                .customer_name(customer.getCustomer_name())
                .customer_email(customer.getCustomer_email())
                .build();
    }

    /**
     * Map to customer entity.
     *
     * @param customerRequestDto the customer request dto
     *
     * @return the customer
     */
    public Customer mapToCustomerEntity(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();
        if (customerRequestDto.getCustomer_name() != null) {
            customer.setCustomer_name(customerRequestDto.getCustomer_name());
        }
        if (customer.getCustomer_address() != null) {
            customer.setCustomer_address(customerRequestDto.getCustomer_address());
        }
        customer.setCustomer_email(customerRequestDto.getCustomer_email());
        return customer;
    }
}
