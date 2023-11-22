package com.upi.emailservices;

import com.upi.emailservices.ServicesInterfaces.EmailServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Email services application.
 */
@SpringBootApplication
public class EmailServicesApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(EmailServicesApplication.class, args);
	}

}

