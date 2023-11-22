package com.upi.autonomoustransactiongateway.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * The type Gateway services.
 */
@Service
public class GatewayServices {


    @Autowired
    private final WebClient webClient;

    /**
     * Instantiates a new Gateway services.
     *
     * @param webClient the web client
     */
    public GatewayServices(WebClient webClient) {
        this.webClient = webClient;
    }


    /**
     * Gets ammount offline services.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the ammount offline services
     */
    public String getAmmountOfflineServices(String customerIdentifier) {
        if (lockTransactionsOfUsers(customerIdentifier)) {
            System.out.println("getAmmountOfflineServices");
            return webClient.put()
                    .uri("http://localhost:8082/bankaccount/setbalance/{customerIdentifier}/{flag}/{ammount}", customerIdentifier, "DEDUCT", 600)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }
        return "false";
    }

    /**
     * Lock transactions of users boolean.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the boolean
     */
    public boolean lockTransactionsOfUsers(String customerIdentifier) {
        System.out.println("lockTransactionsOfUsers");
        return Boolean.TRUE.equals(webClient.put()
                .uri("http://localhost:8082/bankaccount/lockbanktransactions/{customerIdentifier}", customerIdentifier)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }

    /**
     * End offline transaction session boolean.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the boolean
     */
    public boolean endOfflineTransactionSession(String customerIdentifier) {
        return unlockTransactionsOfUsers(customerIdentifier);
    }

    /**
     * Unlock transactions of users boolean.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the boolean
     */
    public boolean unlockTransactionsOfUsers(String customerIdentifier) {
        System.out.println("lockTransactionsOfUsers");
        return Boolean.TRUE.equals(webClient.put()
                .uri("http://localhost:8082/bankaccount/unlockbanktransactions/{customerIdentifier}", customerIdentifier)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }
}
