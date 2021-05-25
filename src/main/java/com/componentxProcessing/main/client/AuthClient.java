package com.componentxProcessing.main.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.componentxProcessing.main.dto.ValidatingDTO;

@FeignClient(name = "auth-client", url = "http://localhost:8081")
public interface AuthClient {

	@GetMapping(value = "/validate")
	public ValidatingDTO getsValidity(@RequestHeader(name = "Authorization", required = true) String token);

}
