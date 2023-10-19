/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
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

package com.upi.account.Services;

import com.upi.account.Entity.Customer;
import com.upi.account.Entity.Upi;
import com.upi.account.Exceptions.UpiAlreadyExistsException;
import com.upi.account.Repositories.CustomerRepository;
import com.upi.account.Repositories.UpiRepository;
import com.upi.account.dto.CustomerRequestDto;
import com.upi.account.dto.DtoServices.CustomerResponseDtoServices;
import com.upi.account.dto.DtoServices.UpiResponseDtoServices;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Upi services.
 */
@Service
public class UpiServices {

    /**
     * The Customer repository.
     */
    @Autowired
    final CustomerRepository customerRepository;
    @Autowired
    private final UpiRepository upiRepository;
    @Autowired
    private final UpiResponseDtoServices upiResponseDtoServices;

    @Autowired
    private final CustomerResponseDtoServices customerResponseDtoServices;

    @Autowired
    private final EntityManager entityManager;

    @Autowired
    private final CustomerService customerService;

    /**
     * Instantiates a new Upi services.
     *
     * @param upiRepository               the upi repository
     * @param customerRepository          the customer repository
     * @param upiResponseDtoServices      the upi response dto services
     * @param customerResponseDtoServices the customer response dto services
     * @param entityManager               the entity manager
     * @param customerService             the customer service
     */
    public UpiServices(UpiRepository upiRepository, CustomerRepository customerRepository, UpiResponseDtoServices upiResponseDtoServices, CustomerResponseDtoServices customerResponseDtoServices, EntityManager entityManager, CustomerService customerService) {
        this.upiRepository = upiRepository;
        this.customerRepository = customerRepository;
        this.upiResponseDtoServices = upiResponseDtoServices;
        this.customerResponseDtoServices = customerResponseDtoServices;
        this.entityManager = entityManager;
        this.customerService = customerService;
    }


    /**
     * Generate new upi id string.
     *
     * @param customerRequestDto the customer request dto
     *
     * @return the string
     */
    @Transactional
    public void generateNewUpiId(CustomerRequestDto customerRequestDto) throws UpiAlreadyExistsException {
        Customer customer = customerRepository.findByEmail(customerRequestDto.getCustomer_email());
//        upi.setUpi_of_customer(customer);
        if (upiRepository.findByUpiId(generateUpiUsingEmail(customer)) == null) {
            Upi upi = new Upi(customer);
            upi.setUpi_id(generateUpiUsingEmail(customer));
            upi.setUpi_password(generateUpiUsingEmail(customer));
            upiRepository.save(upi);
        }
        throw new UpiAlreadyExistsException("Upi Already Generated.");
//        UpiResponseDto upiResponseDto = upiResponseDtoServices.mapUpiEntityT0UpiResponseDto(upi);
//        return upiResponseDto;
        /**
         *
         * Generate Entity Manager Factory Using JPAUtil
         *
         * Create Entity Manager Using Entity Manager Factory
         *
         *

         EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
         System.out.println("Exce1");
         Customer customer = customerService.findCustomerByEmail(customerRequestDto.getCustomer_email());
         System.out.println("Exce2");
         Upi upi1 = new Upi();
         upi1.setId(234L);
         System.out.println(upi1);
         upi1.setUpi_id(generateUpiUsingEmail(customer));
         System.out.println("Exce1");
         upi1.setUpi_of_customer(customerService.findCustomerByEmail(customerRequestDto.getCustomer_email()));
         UpiResponseDto generatedUpi = upiResponseDtoServices.mapUpiEntityT0UpiResponseDto(upi1);
         System.out.println("Exce1");
         customer.getAdded_upi().add(upi1);
         try {
         entityManager.getTransaction().begin();
         entityManager.persist(upi1);
         entityManager.persist(customer);
         System.out.println("persisted upi"+upi1);
         entityManager.getTransaction().commit();
         } catch (Exception e) {
         if (entityManager.getTransaction().isActive()) {
         entityManager.getTransaction().rollback();
         }
         e.printStackTrace();
         //            throw TransactionFailedException("Transaction Failed", e);
         } finally {
         entityManager.close();
         }
         return generatedUpi;*/
    }


    /**
     * Generate new upi id string.
     *
     * @param customer the customer request dto
     *
     * @return the string
     */
    private String generateUpiUsingEmail(Customer customer) {
        return customer.getCustomer_email().split("@")[0] + "@oksbi";
    }

    /**
     * Save object.
     *
     * @param upi the upi
     *
     * @return the object
     */
    public Object save(Upi upi) {
//        upi.setUpi_of_customer(customerService.findCustomerByEmail(upi.getUpi_id().split("@")[0]+"@gmail.com"));
        return upiRepository.save(upi);
    }

    /**
     * Gets all upi.
     *
     * @return the all upi
     */
    public List<Upi> getAllUpi() {
        return upiRepository.findAll();
    }
}
