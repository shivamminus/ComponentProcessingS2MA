package com.componentxProcessing.main.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.componentxProcessing.main.dto.PackagingAndDeliveryDTO;

import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient(name = "payment-service", url = "localhost:8082/payment")
public interface PaymentClient {

	@PostMapping("/processpayment/{requestId}/{cardNumber}/{creditLimit}/{processingCharge}")
	PackagingAndDeliveryDTO paymentDetails( @PathVariable String requestId,@PathVariable int cardNumber,
			@PathVariable int creditLimit, @PathVariable int processingCharge,@RequestHeader(name = "Authorization", required = true) String token);

}
