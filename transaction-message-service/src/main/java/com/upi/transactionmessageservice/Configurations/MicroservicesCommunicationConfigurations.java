/*
 * ----------------------------------------------------------------------------
 *
 *     This file is part of the e-edu project.
 *     @Author RB Mhetre
 *     @Generated 2023
 *
 *     ----------------------------------------------------------------------------
 */

package com.upi.transactionmessageservice.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * The type Microservices communication configurations.
 */
@Configuration
public class MicroservicesCommunicationConfigurations {

    /**
     * Web client web client.
     *
     * @return the web client
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
