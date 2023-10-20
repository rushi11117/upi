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

import com.upi.account.Entity.Upi;
import com.upi.account.Exceptions.UpiAlreadyExistsException;
import com.upi.account.Services.UpiServices;
import com.upi.account.dto.CustomerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Upi controller.
 */
@RestController
@RequestMapping("/upi")
public class UpiController {

    @Autowired
    private static UpiServices upiServices;

    /**
     * Instantiates a new Upi controller.
     *
     * @param upiServices the upi services
     */
    public UpiController(UpiServices upiServices) {
        this.upiServices = upiServices;
    }

    /**
     * Generate upi id.
     *
     * @param customerRequestDto the customer request dto
     *
     * @throws UpiAlreadyExistsException the upi already exists exception
     */
    @GetMapping("/new")
    public void generateUpiId(@RequestBody CustomerRequestDto customerRequestDto) throws UpiAlreadyExistsException {
//        ResponseEntity.status(409).body(upiServices.generateNewUpiId(customerRequestDto));
        upiServices.generateNewUpiId(customerRequestDto);
    }

    /**
     * Save upi id response entity.
     *
     * @param upi the upi
     *
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity saveUpiId(@RequestBody Upi upi) {
        return ResponseEntity.status(200).body(upiServices.save(upi));
    }

    /**
     * Gets all upi.
     *
     * @return the all upi
     */
    @GetMapping("/all")
    public List<Upi> getAllUpi() {
        return upiServices.getAllUpi();
    }
}
