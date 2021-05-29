package com.componentxProcessing.main.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.componentxProcessing.main.client.AuthClient;
import com.componentxProcessing.main.dto.ValidatingDTO;
import com.componentxProcessing.main.exceptions.InvalidTokenException;
import com.componentxProcessing.main.model.ProcessRequest;
import com.componentxProcessing.main.model.ProcessResponse;
import com.componentxProcessing.main.service.ReplacementServiceImpl;

@SpringBootTest
public class ComponentProcessingControllerTest {

	ComponentProcessingController componentProcessingController = new ComponentProcessingController();
	
	@Mock
	ReplacementServiceImpl replacementServiceImplObj;
	
	
//	@Autowired
	@Mock
	AuthClient authClient;

	@Test
	@DisplayName("Checking for ComponentProcessingController - if it is loading or not for @GetMapping")
	void componentProcessingControllerNotNullTest() {
		assertThat(componentProcessingController).isNotNull();
	}

	/*
	 * Test for Valid token
	 */
	@Test
	void testProcessDetailAPI() throws InvalidTokenException{

		ProcessRequest processRequest = new ProcessRequest(1,"satyam",7982267437L, "798798798797987", true, "Accessory", "Mouse", 4);
		String token = "token";
		when(authClient.getsValidity(token)).thenReturn(new ValidatingDTO(true));
//		when(authClient.getsValidity(token).isValidStatus()).thenReturn(true);
		
		when(replacementServiceImplObj.processService(processRequest, "token")).thenReturn(new ProcessResponse("1234", 500,(double) 569, "05-06-2020"));
		System.out.println(componentProcessingController.processResponseDetails(processRequest, token));
		assertEquals(200, componentProcessingController.processResponseDetails(processRequest, token).getStatusCodeValue());

	}

}
