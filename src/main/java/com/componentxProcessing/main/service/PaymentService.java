package com.componentxProcessing.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componentxProcessing.main.client.PaymentClient;
import com.componentxProcessing.main.dto.PaymentDetailDTO;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentClient paymentClient;
	
	public String messageConfirmation(String requestId, String creditCardNumber, Integer creditLimit,
			Integer processingCharge, String token) {
		System.out.println("Inside Service");
		double check = (paymentClient.paymentDetails(requestId, creditCardNumber, creditLimit, processingCharge, token))
				.getCharge().doubleValue();

		if ((int) check > 0) {

			System.out.println("Successful Operation Message displayed");
			return "Operation Successful";
		} else {
			return "Operation Not Successful";
		}
	}
}
