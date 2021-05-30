package com.componentxProcessing.main.dto;

public class PaymentChargeDTO {
	private Double charge;

	public PaymentChargeDTO(Double charge) {
		this.charge = charge;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}
}
