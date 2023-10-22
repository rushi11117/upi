package com.upi.bankupicoord.APIGateWays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class BankAPIGateWay {

    private static String microserviceBaseUrlAccountService = "http://localhost:8081";
    private static WebClient webClientAccountService = WebClient.create(microserviceBaseUrlAccountService);

    @GetMapping
    public Mono<ResponseEntity<String>> getAllCustomers() {
        return webClientAccountService.get()
                .uri("/customer/")
                .retrieve()
                .toEntity(String.class);
    }

}
