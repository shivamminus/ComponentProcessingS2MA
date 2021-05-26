package com.componentxProcessing.main.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componentxProcessing.main.client.PackagingClient;
import com.componentxProcessing.main.client.PaymentClient;
import com.componentxProcessing.main.model.ProcessRequest;
import com.componentxProcessing.main.model.ProcessResponse;
import com.componentxProcessing.main.repository.ProcessRequestRepo;
import com.componentxProcessing.main.repository.ProcessResponseRepo;
import com.componentxProcessing.main.util.Utilities;

@Service
public class ReplacementServiceImpl implements ProcessRequestService {

	@Autowired
	private ProcessRequestRepo processRequestRepo;
	@Autowired
	private ProcessResponse processResponseObj;
	@Autowired
	private ProcessResponseRepo processResponseRepo;
	@Autowired
	private PackagingClient packagingClient;

	@Override
	public ProcessResponse processService(ProcessRequest processRequestObj, String token) {

		System.out.println("hi im here " + processRequestObj.toString());
		String strDate;
		if (processRequestObj.getIsPriorityRequest()) {
			
			strDate = LocalDateTime.now().plusDays(2).toString();
			
		} else {

			strDate = LocalDateTime.now().plusDays(5).toString();
		}
		processResponseObj.setDateOfDelivery(strDate);
		processResponseObj.setProcessingCharge(300);

		processResponseObj.setRequestId(Utilities.generateRequestId());
		processRequestRepo.save(processRequestObj);

		processResponseObj.setPackagingAndDeliveryCharge(packagingClient
				.save(processRequestObj.getComponentType(), processRequestObj.getQuantity(), token).getCharge());
		processResponseRepo.save(processResponseObj);

		return processResponseObj;
	}

}
