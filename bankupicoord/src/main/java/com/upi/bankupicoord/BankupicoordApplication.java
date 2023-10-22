package com.upi.bankupicoord;

import com.upi.bankupicoord.APIGateWays.BankAPIGateWay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class BankupicoordApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankupicoordApplication.class, args);
//		Mono<ResponseEntity<String>> mono =new BankAPIGateWay().getAllCustomers();
//		mono.flatMap(data -> {
//			System.out.println("Data received: " + data);
//			// You can perform further transformations or actions here
//			return Mono.just(data);
//		}).subscribe();
//		System.out.println(new BankAPIGateWay().getAllCustomers().subscribe());
	}

}
