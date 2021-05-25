package com.componentxProcessing.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ComponentProcessingS2MaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComponentProcessingS2MaApplication.class, args);
	}

}
