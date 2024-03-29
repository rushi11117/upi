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
import org.springframework.data.repository.query.Param;
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
    private final UpiServices upiServices;

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
     * @param customerIdentifier the customer identifier
     *
     * @throws UpiAlreadyExistsException the upi already exists exception
     */
    @GetMapping("/new/{customerIdentifier}")
    public void generateUpiId(@PathVariable String customerIdentifier) throws UpiAlreadyExistsException {
        upiServices.generateNewUpiId(customerIdentifier);
    }


    /**
     * Update upi password response entity.
     *
     * @param email       the email
     * @param newPassword the new password
     *
     * @return the response entity
     */
    @PostMapping({"/addpassword/{email}/{newPassword}", "/updatepassword/{email}/{newPassword}"})
    public ResponseEntity updateUpiPassword(
            @PathVariable String email,
            @PathVariable String newPassword
    ) {
        if(upiServices.updateUpiPassword(email, newPassword)) {
            return ResponseEntity.ok().body("Updated");
        }
        return ResponseEntity.ok().body("Unexpected Error!!!");
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

    @GetMapping("/gmailofupi/{customerUpi}")
    public String getGmailOfUpi (@PathVariable String customerUpi) {
        return upiServices.getGmailOfUpi(customerUpi);
    }
}
