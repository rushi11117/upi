package com.upi.autonomoustransactiongateway.Controllers;

import com.upi.autonomoustransactiongateway.Enums.AccountLockStatus;
import com.upi.autonomoustransactiongateway.Services.GatewayServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * The type Gateway controller.
 */
@RestController
public class GatewayController {

    @Autowired
    private final WebClient webClient;

    @Autowired
    private final GatewayServices gatewayServices;


    /**
     * Instantiates a new Gateway controller.
     *
     * @param webClient       the web client
     * @param gatewayServices the gateway services
     */
    public GatewayController(WebClient webClient,
//            ServerUriEnums serverUriEnums
                             GatewayServices gatewayServices) {
        this.webClient = webClient;
//        this.serverUriEnums = serverUriEnums;
        this.gatewayServices = gatewayServices;
    }

    /**
     * Gets ammount offline.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the ammount offline
     */
    @GetMapping("/fillpocket/{customerIdentifier}")
    public String getAmmountOffline(@PathVariable String customerIdentifier) {
        System.out.println("getAmmountOfflineController");
        return gatewayServices.getAmmountOfflineServices(customerIdentifier);
    }

    /**
     * End offline transaction session string.
     *
     * @param customerIdentifier the customer identifier
     *
     * @return the boolean
     */
    @GetMapping("/endofflinetransactionsession/{customerIdentifier}")
    public Boolean endOfflineTransactionSession(@PathVariable String customerIdentifier) {
        System.out.println("endOfflineTransactionSession");
        return gatewayServices.endOfflineTransactionSession(customerIdentifier);
    }

    @GetMapping("/getlockflagstatus")
    public AccountLockStatus getlockFlagStatus(String customerIdentifier) {

    }
}
