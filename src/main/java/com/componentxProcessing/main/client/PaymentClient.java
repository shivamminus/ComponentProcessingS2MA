package com.componentxProcessing.main.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.componentxProcessing.main.dto.PackagingAndDeliveryDTO;

import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient(name = "payment-service", url = "localhost:9090/payment")
public interface PaymentClient {

	@GetMapping("/processpayment")
	PackagingAndDeliveryDTO paymentDetails( @RequestParam String requestId,@RequestParam String cardNumber,
			@RequestParam int creditLimit, @RequestParam int processingCharge,@RequestHeader(name = "Authorization", required = true) String token);

}
