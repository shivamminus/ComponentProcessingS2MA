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
public class RepairServiceImpl implements ProcessRequestService{

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
    @Override
    public ProcessResponse processService(ProcessRequest processRequestObj,String token){
    	System.out.println("Before Priority Check");
        if(processRequestObj.getIsPriorityRequest()){
        	System.out.println("Priority True");
        	 processResponseObj.setProcessingCharge(700); 
         	System.out.println("Priority True23");
     		String strDate = LocalDateTime.now().plusDays(2).toString();  
     		System.out.println(strDate);
     		processResponseObj.setDateOfDelivery(strDate);
        }
        else{
        	System.out.println("Priority False");
        	 processResponseObj.setProcessingCharge(500); 
     		String strDate = LocalDateTime.now().plusDays(2).toString(); 
     		processResponseObj.setDateOfDelivery(strDate);
        }
        System.out.println("After Priority Check");
        System.out.println("Utilities.getAlphaNumericString(16)"+Utilities.getAlphaNumericString(16));
        processResponseObj.setRequestId(Utilities.getAlphaNumericString(16));
        processRequestRepo.save(processRequestObj);
        
        processResponseObj.setPackagingAndDeliveryCharge(packagingClient.save(processRequestObj.getComponentType(), processRequestObj.getQuantity(),token).getCharge());
        processResponseRepo.save(processResponseObj);
   
        return processResponseObj;
    }
   @Override
    public String generateRequestId(){

        return UUID.randomUUID().toString();
        
    }

	@Override
	public String messageConfirmation( String requestId, Integer creditCardNumber, Integer creditLimit,
			Integer processingCharge,String token) {
		System.out.println("Inside Service");
		double check = (paymentClient.paymentDetails(requestId,creditCardNumber, creditLimit, processingCharge,token)).getCharge().doubleValue();
	
    	 if((int)check>0) {
      	   
      	   System.out.println("Successful Operation Message displayed");
      	   return "Operation Successful";
         }
         else {
      	   return "Operation Not Successful";
         }
	}
    
}
