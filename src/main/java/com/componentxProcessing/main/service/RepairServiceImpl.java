package com.componentxProcessing.main.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componentxProcessing.main.client.PackagingClient;
import com.componentxProcessing.main.client.PaymentClient;
import com.componentxProcessing.main.exceptions.SomethingWentWrongException;
import com.componentxProcessing.main.model.ProcessRequest;
import com.componentxProcessing.main.model.ProcessResponse;
import com.componentxProcessing.main.repository.ProcessRequestRepo;
import com.componentxProcessing.main.repository.ProcessResponseRepo;
import com.componentxProcessing.main.util.Utilities;

@Service
public class RepairServiceImpl implements ProcessRequestService {
	private static Logger logger = LoggerFactory.getLogger(ReplacementServiceImpl.class);

	@Autowired
	private ProcessResponse processResponseObj;
	@Autowired
	private ProcessRequestRepo processRequestRepo;
	@Autowired
	private ProcessResponseRepo processResponseRepo;
	@Autowired
	private PackagingClient packagingClient;
	@Autowired
	private PaymentClient paymentClient;

	/*
	 * This function will return process response
	 * 
	 * @header String token
	 * 
	 * @params ProcessRequestObj processRequestObj
	 * 
	 * @return ProcessResponse
	 */
	@Override
	public ProcessResponse processService(ProcessRequest processRequestObj, String token)
			throws SomethingWentWrongException {
		logger.info("Before Priority Check");
		if (processRequestObj.getIsPriorityRequest()) {
			logger.info("Priority True");
			processResponseObj.setProcessingCharge(700);
			logger.info("Priority True23");
			String strDate = LocalDateTime.now().plusDays(2).toString();
			logger.info(strDate);
			processResponseObj.setDateOfDelivery(strDate);
		} else {
			logger.info("Priority False");
			processResponseObj.setProcessingCharge(500);
			String strDate = LocalDateTime.now().plusDays(2).toString();
			processResponseObj.setDateOfDelivery(strDate);
		}
		logger.info("After Priority Check");
		logger.info("Utilities.getAlphaNumericString(16)" + Utilities.getAlphaNumericString(16));
		processResponseObj.setRequestId(Utilities.generateRequestId());
		processRequestRepo.save(processRequestObj);

		try {
			processResponseObj.setPackagingAndDeliveryCharge(packagingClient
					.save(processRequestObj.getComponentType(), processRequestObj.getQuantity(), token).getCharge());
			processResponseRepo.save(processResponseObj);
		} catch (Exception SomethingWentWrongException) {
			// TODO: handle exception
			throw new SomethingWentWrongException("Something Went Wrong... Please check your Details");
		}

		return processResponseObj;
	}

}
