package com.componentxProcessing.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.componentxProcessing.main.ComponentProcessingS2MaApplication;
import com.componentxProcessing.main.client.AuthClient;
import com.componentxProcessing.main.dto.PaymentStatusDTO;
import com.componentxProcessing.main.dto.ValidatingDTO;
import com.componentxProcessing.main.exceptions.ComponentTyepNotFoundException;
import com.componentxProcessing.main.exceptions.InvalidTokenException;
import com.componentxProcessing.main.model.ProcessRequest;
import com.componentxProcessing.main.model.ProcessResponse;
import com.componentxProcessing.main.service.PaymentService;
import com.componentxProcessing.main.service.RepairServiceImpl;
import com.componentxProcessing.main.service.ReplacementServiceImpl;

/*
 * component service controller
*/
@RestController
@RequestMapping(value = "/componentservice")
public class ComponentProcessingController {
	private static Logger logger = LoggerFactory.getLogger(ComponentProcessingS2MaApplication.class);

	@Autowired
	private RepairServiceImpl repairServiceImplObj;
	@Autowired
	private ReplacementServiceImpl replacementServiceImplObj;
	@Autowired
	private ProcessResponse processResponseObj;
	@Autowired
	private AuthClient authClient;

	@Autowired
	private PaymentService paymentService;

	/*
	 * This function will return process response
	 * 
	 * @header String token
	 * 
	 * @params ProcessRequestObj processRequestObj
	 * 
	 * @return ProcessResponse
	 */
	@PostMapping(path = "/ProcessDetail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProcessResponse> processResponseDetails(@RequestBody ProcessRequest processRequestObj,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {

		logger.info(token);
		ValidatingDTO validatingDTO = authClient.getsValidity(token);
		if (!validatingDTO.isValidStatus()) {

			throw new InvalidTokenException("Token is either expired or invalid...");
		}

		logger.info("Checking if component is Integral or Accessory - BEGINS");
		if (("Integral").equalsIgnoreCase(processRequestObj.getComponentType())) {
			logger.info("Retrieving the ProcessResponse object for Integral - BEGINS");

			processResponseObj = repairServiceImplObj.processService(processRequestObj, token);

			logger.info("Retrieving the ProcessResponse object for Integral - ENDS");
		} else if (("Accessory").equalsIgnoreCase(processRequestObj.getComponentType())) {
			logger.info("Retrieving the ProcessResponse object for Accessory - BEGINS");

			processResponseObj = replacementServiceImplObj.processService(processRequestObj, token);

			logger.info("Retrieving the ProcessResponse object for Accessory - ENDS");
		} else {
			throw new ComponentTyepNotFoundException("Wrong Component Type");
		}

		logger.info("Checking if component is Integral or Accessory - ENDS");

		return new ResponseEntity<>(processResponseObj, HttpStatus.OK);

	}

	/*
	 * This function will return Payment Status
	 * 
	 * @header String token
	 * 
	 * @params String requestId
	 * 
	 * @params String creditCardNumber
	 * 
	 * @params Integer creditLimit
	 * 
	 * @params Integer processingCharge
	 * 
	 * @return PaymentStatusDTO
	 */
	@PostMapping(path = "/completeProcessing/{requestId}/{creditCardNumber}/{creditLimit}/{processingCharge}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentStatusDTO> messageConfirmation(@PathVariable String requestId,
			@PathVariable String creditCardNumber, @PathVariable Integer creditLimit,
			@PathVariable Integer processingCharge,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {

		logger.info(token);

		if (!authClient.getsValidity(token).isValidStatus()) {

			throw new InvalidTokenException("Token is either expired or invalid...");
		}
		logger.info("Controller Component");
		try {
			return new ResponseEntity<>(new PaymentStatusDTO(paymentService.messageConfirmation(requestId,
					creditCardNumber, creditLimit, processingCharge, token)), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("FORBIDDEN REQUEST");
			return new ResponseEntity<>(new PaymentStatusDTO(paymentService.messageConfirmation(requestId,
					creditCardNumber, creditLimit, processingCharge, token)), HttpStatus.FORBIDDEN);
		}

	}

	@GetMapping(path = "/health-check")
	public ResponseEntity<String> healthCheck() {
		logger.info("ComponentProcessing Microservice is Up and Running....");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

}