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


package com.upi.account.dto;

import lombok.Data;

/**
 * The type Customer request dto.
 */
@Data
public class CustomerRequestDto {
    private String customer_name;
    private String customer_email;
    private String customer_address;
}
