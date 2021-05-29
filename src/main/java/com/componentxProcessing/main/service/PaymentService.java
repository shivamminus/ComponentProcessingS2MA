package com.componentxProcessing.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componentxProcessing.main.client.PaymentClient;
import com.componentxProcessing.main.dto.PaymentDetailDTO;

@Service
public class PaymentService {
	private static Logger logger = LoggerFactory.getLogger(ReplacementServiceImpl.class);
	
	@Autowired
	private PaymentClient paymentClient;
	
	/*
	 * This function will return Payment Status
	 * 
	 * @params String token
	 * 
	 * @params String requestId
	 * 
	 * @params String creditCardNumber
	 * 
	 * @params Integer creditLimit
	 * 
	 * @params Integer processingCharge
	 * 
	 * @return String message
	 */
	public String messageConfirmation(String requestId, String creditCardNumber, Integer creditLimit,
			Integer processingCharge, String token) {
		logger.info("Inside Service");
		double check = (paymentClient.paymentDetails(requestId, creditCardNumber, creditLimit, processingCharge, token))
				.getCharge().doubleValue();

		if ((int) check > 0) {

			logger.info("Successful Operation Message displayed");
			return "Operation Successful";
		} else {
			return "Operation Not Successful";
		}
	}
}
