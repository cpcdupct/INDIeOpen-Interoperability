package com.cpcd.microservices.app.lti.microserviciolti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class MicroservicioltiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioltiApplication.class, args);
	}

}
