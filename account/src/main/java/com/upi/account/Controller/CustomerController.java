/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *
 *     ----------------------------------------------------------------------------
 */

package com.upi.account.Controller;

import com.upi.account.Entity.Customer;
import com.upi.account.Services.CustomerService;
import com.upi.account.dto.CustomerRequestDto;
import com.upi.account.dto.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The type Customer controller.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private static CustomerService customerService;

    /**
     * Instantiates a new Customer controller.
     *
     * @param customerService the customer service
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Create customer response entity.
     *
     * @param customer the customer
     *
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity createCustomer(@RequestBody Customer customer) {
        return customerService.createUser(customer);
    }

    /**
     * Update customer response entity.
     *
     * @param customerRequestDto the customer request dto
     *
     * @return the response entity
     */
    @PutMapping
    public ResponseEntity updateCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(customerRequestDto));
    }

    /**
     * Gets customer by id.
     *
     * @param customer_id the customer id
     *
     * @return the customer by id
     */
    @GetMapping("/{customer_id}")
    public ResponseEntity getCustomerById(@PathVariable Long customer_id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(customerService.findUserById(customer_id));
    }

    /**
     * Gets all customer.
     *
     * @return the all customer
     */
    @GetMapping("/")
    public List<CustomerResponseDto> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    /**
     * Is customer exists boolean.
     *
     * @param customer_email the customer email
     *
     * @return the boolean
     */
    @GetMapping("/email/{customer_email}")
    public Boolean isCustomerExists(@PathVariable String customer_email) {
        return customerService.isCustomerExists(customer_email + "@gmail.com");
    }
}
