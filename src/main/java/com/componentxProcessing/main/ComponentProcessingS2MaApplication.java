package com.componentxProcessing.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ComponentProcessingS2MaApplication {

	private static Logger logger = LoggerFactory.getLogger(ComponentProcessingS2MaApplication.class);

	
	public static void main(String[] args) {
        System.setProperty("server.connection-timeout","120000");

        logger.info("Component Processing Server Starting");
		SpringApplication.run(ComponentProcessingS2MaApplication.class, args);
		
	}

}
