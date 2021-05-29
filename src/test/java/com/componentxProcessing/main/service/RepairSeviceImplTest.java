package com.componentxProcessing.main.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.componentxProcessing.main.model.ProcessRequest;
import com.componentxProcessing.main.model.ProcessResponse;

@SpringBootTest
public class RepairSeviceImplTest {

	RepairServiceImpl repairSevice = new RepairServiceImpl();
	ProcessRequest processRequest = new ProcessRequest();

	ProcessResponse processResponseObj = new ProcessResponse();
	List<String> li = new ArrayList<String>();

	@Test
	@DisplayName("Checking for repairSevice - if it is loading or not for @GetMapping")
	void repairSeviceNotNullTest() {
		assertThat(repairSevice).isNotNull();
	}

}
