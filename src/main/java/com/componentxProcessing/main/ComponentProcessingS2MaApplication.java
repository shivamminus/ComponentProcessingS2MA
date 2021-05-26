package com.componentxProcessing.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ComponentProcessingS2MaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComponentProcessingS2MaApplication.class, args);
	}

}
