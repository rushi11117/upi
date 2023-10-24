package com.upi.bankupicoord.APIGateWays;

import com.upi.bankupicoord.Enums.ServerUriEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * The type Bank api gate way.
 */
@RestController
public class BankAPIGateWay {

    @Autowired
    private final WebClient webClient;

    /**
     * Instantiates a new Bank api gate way.
     *
     * @param webClient the web client
     */
    public BankAPIGateWay(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Gets all customers.
     *
     * @return the all customers
     */
    @GetMapping("/")
    public Mono<ResponseEntity<String>> getAllCustomers() {
        return webClient.get()
                .uri(ServerUriEnums.BANK_SERVER_URI.getServiceUri() + "/bankcustomer/all")
                .retrieve()
                .toEntity(String.class);
    }

    /**
     * Generate upi for bank account.
     *
     * @param bankAccount the bank account
     */
    @PostMapping("/")
    public void generateUpiForBankAccount(@RequestBody Object bankAccount) {
        webClient.post()
                .uri(ServerUriEnums.UPI_SERVER_URI.getServiceUri())
                .bodyValue(bankAccount)
                .retrieve()
                .bodyToMono(String.class);
    }


    /**
     * Gets all upis.
     *
     * @return the all upis
     */
    @GetMapping("/upis")
    public Mono<ResponseEntity<String>> getAllUpis() {
        return webClient.get()
                .uri(ServerUriEnums.UPI_SERVER_URI.getServiceUri()+"/upi/all")
                .retrieve()
                .toEntity(String.class);
    }

}