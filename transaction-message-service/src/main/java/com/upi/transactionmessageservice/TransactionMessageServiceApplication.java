package com.upi.transactionmessageservice;

import com.upi.transactionmessageservice.Services.MicroservicesCommunicationServices;
import com.upi.transactionmessageservice.Services.TransactionUsingUpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Transaction message service application.
 */
@SpringBootApplication
public class TransactionMessageServiceApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(TransactionMessageServiceApplication.class, args);
	}

}
