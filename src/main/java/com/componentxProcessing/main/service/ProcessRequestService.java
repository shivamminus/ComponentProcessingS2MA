package com.componentxProcessing.main.service;

import org.springframework.stereotype.Service;

import com.componentxProcessing.main.model.ProcessRequest;
import com.componentxProcessing.main.model.ProcessResponse;

@Service
public interface ProcessRequestService {

	public ProcessResponse processService(ProcessRequest processRequestObj, String token);
}
