package com.componentxProcessing.main.dto;

import javax.persistence.Id;

public class PaymentStatusDTO {

	@Id
	private String status;

	public PaymentStatusDTO() {
		super();
	}

	public PaymentStatusDTO(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PaymentStatusDTO [status=" + status + "]";
	}

}