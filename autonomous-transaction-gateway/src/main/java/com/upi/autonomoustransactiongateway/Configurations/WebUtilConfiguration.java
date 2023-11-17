package com.upi.autonomoustransactiongateway.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * The type Web util configuration.
 */
@Configuration
public class WebUtilConfiguration {

    /**
     * Web client web client.
     *
     * @return the web client
     */
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
