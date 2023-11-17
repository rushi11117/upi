package com.upi.autonomoustransactiongateway.Configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConnfiguration {

    @Bean
    public String myStringBean() {
        return "http://localhost:8082";
    }

    @Bean
    public int myIntBean() {
        return 42;
    }

}
