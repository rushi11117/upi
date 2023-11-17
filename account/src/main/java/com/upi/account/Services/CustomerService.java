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

package com.upi.account.Services;

import com.upi.account.Entity.Customer;
import com.upi.account.Repositories.CustomerRepository;
import com.upi.account.dto.CustomerRequestDto;
import com.upi.account.dto.CustomerResponseDto;
import com.upi.account.dto.DtoServices.CustomerResponseDtoServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Customer service.
 */
@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final CustomerResponseDtoServices customerResponseDtoServices;


    /**
     * Instantiates a new Customer service.
     *
     * @param customerRepository          the customer repository
     * @param customerResponseDtoServices the customer response dto services
     */
    public CustomerService(CustomerRepository customerRepository, CustomerResponseDtoServices customerResponseDtoServices) {
        this.customerRepository = customerRepository;
        this.customerResponseDtoServices = customerResponseDtoServices;
    }

    /**
     * Create user customer.
     *
     * @param customer the customer
     *
     * @return the customer
     */
    public ResponseEntity createUser(Customer customer) {
        if (isCustomerExists(customer.getCustomer_email())) {
            return ResponseEntity.status(HttpStatus.resolve(409)).body("USER_ALREADY_EXISTS");
        } else {
            Customer createdCustomer = customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(customerResponseDtoServices.mapToCustomerResponseDto(createdCustomer));
        }

    }

    /**
     * Find user by id customer response dto.
     *
     * @param customerId the customer id
     *
     * @return the customer response dto
     */
    public CustomerResponseDto findUserById(Long customerId) {
        return customerResponseDtoServices.mapToCustomerResponseDto(customerRepository.findById(customerId).get());
    }

    /**
     * Gets all customers.
     *
     * @return the all customers
     */
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream().map(customer -> customerResponseDtoServices.mapToCustomerResponseDto(customer))
                .collect(Collectors.toList());
    }

    /**
     * Is customer exists boolean.
     *
     * @param customerEmail the customer email
     *
     * @return the boolean
     */
    public Boolean isCustomerExists(String customerEmail) {
        Customer customer = customerRepository.findByEmail(customerEmail);
        return customer != null;
    }


    /**
     * Find customer by email customer.
     *
     * @param customerEmail the customer email
     *
     * @return the customer
     */
    public Customer findCustomerByEmail(String customerEmail) {
        return customerRepository.findByEmail(customerEmail);
    }

    /**
     * Update customer int.
     *
     * @param customerRequestDto the customer request dto
     *
     * @return the int
     */
    @Transactional
    public int updateCustomer(CustomerRequestDto customerRequestDto) {
        return customerRepository.updateByEmail(customerRequestDto.getCustomer_email(), customerRequestDto.getCustomer_name(), customerRequestDto.getCustomer_address());
    }

    /**
     * Gets phone number of upi.
     *
     * @param customerUpi the customer upi
     *
     * @return the phone number of upi
     */
    public String getPhoneNumberOfUpi(String customerUpi) {
        String phoneNumber = customerRepository.getPhoneNumberOfUpi(customerUpi).get(0);
        System.out.println(phoneNumber);
        if (phoneNumber == null) {
            return "Phone Number Not Added";
        }
        return phoneNumber;
    }
}
