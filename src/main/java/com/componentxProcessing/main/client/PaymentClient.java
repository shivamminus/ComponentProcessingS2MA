package com.componentxProcessing.main.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.componentxProcessing.main.dto.PackagingAndDeliveryDTO;
import com.componentxProcessing.main.dto.PaymentChargeDTO;

import feign.Headers;


/*
 * Call Payment Service to initiate charge from user
*/
@Headers("Content-Type: application/json")
//@FeignClient(name = "payment-service", url = "localhost:8082/payment")
@FeignClient(name = "payment-service", url = "localhost:8082/payment")

public interface PaymentClient {

	@GetMapping("/processpayment")
	PackagingAndDeliveryDTO paymentDetails( @RequestParam String requestId,@RequestParam String cardNumber,
			@RequestParam int creditLimit, @RequestParam int processingCharge,@RequestHeader(name = "Authorization", required = true) String token);

}
