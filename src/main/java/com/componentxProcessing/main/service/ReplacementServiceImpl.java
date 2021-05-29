package com.componentxProcessing.main.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componentxProcessing.main.ComponentProcessingS2MaApplication;
import com.componentxProcessing.main.client.PackagingClient;
import com.componentxProcessing.main.client.PaymentClient;
import com.componentxProcessing.main.exceptions.SomethingWentWrongException;
import com.componentxProcessing.main.model.ProcessRequest;
import com.componentxProcessing.main.model.ProcessResponse;
import com.componentxProcessing.main.repository.ProcessRequestRepo;
import com.componentxProcessing.main.repository.ProcessResponseRepo;
import com.componentxProcessing.main.util.Utilities;

@Service
public class ReplacementServiceImpl implements ProcessRequestService {
	private static Logger logger = LoggerFactory.getLogger(ReplacementServiceImpl.class);

	@Autowired
	private ProcessRequestRepo processRequestRepo;
	@Autowired
	private ProcessResponse processResponseObj;
	@Autowired
	private ProcessResponseRepo processResponseRepo;
	@Autowired
	private PackagingClient packagingClient;

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
	public ProcessResponse processService(ProcessRequest processRequestObj, String token) throws SomethingWentWrongException{

		logger.info("hi im here " + processRequestObj.toString());
		String strDate;
		if (processRequestObj.getIsPriorityRequest()) {
			
			strDate = LocalDateTime.now().plusDays(2).toString();
			
		} else {

			strDate = LocalDateTime.now().plusDays(5).toString();
		}
		processResponseObj.setDateOfDelivery(strDate);
		processResponseObj.setProcessingCharge(300);

		processResponseObj.setRequestId(Utilities.generateRequestId());
		try {
			
			processRequestRepo.save(processRequestObj);
			
			processResponseObj.setPackagingAndDeliveryCharge(packagingClient
					.save(processRequestObj.getComponentType(), processRequestObj.getQuantity(), token).getCharge());
			processResponseRepo.save(processResponseObj);
		}
		catch (Exception SomethingWentWrongException) {
			// TODO: handle exception
			logger.error("Cant commit to DB");
			throw new SomethingWentWrongException("Something Went Wrong... Please check your Details");
			
		}
		return processResponseObj;
	}

}
