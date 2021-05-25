package com.componentxProcessing.main.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PackagingAndDeliveryDTO {

	private Double charge;

	public PackagingAndDeliveryDTO() {
		super();
	}

	public PackagingAndDeliveryDTO(Double charge) {
		this.charge = charge;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

}
