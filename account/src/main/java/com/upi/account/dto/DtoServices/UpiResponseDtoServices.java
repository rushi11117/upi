/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *
 *     ----------------------------------------------------------------------------
 */

package com.upi.account.dto.DtoServices;

import com.upi.account.Entity.Upi;
import com.upi.account.dto.UpiResponseDto;
import org.springframework.stereotype.Service;

/**
 * The type Upi response dto services.
 */
@Service
public class UpiResponseDtoServices {

    /**
     * Map upi entity t 0 upi response dto upi response dto.
     *
     * @param upi the upi
     *
     * @return the upi response dto
     */
    public UpiResponseDto mapUpiEntityT0UpiResponseDto(Upi upi) {
        return UpiResponseDto.builder()
                .upi_id(upi.getUpi_id())
                .upi_of_customer(upi.getUpi_of_customer())
                .build();
    }
}
