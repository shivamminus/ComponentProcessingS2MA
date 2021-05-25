package com.componentxProcessing.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidatingDTO {

	@JsonProperty
	private boolean validStatus;

	public ValidatingDTO() {
		super();
	}

	public ValidatingDTO(boolean validStatus) {
		super();
		this.validStatus = validStatus;
	}

	public boolean isValidStatus() {
		return validStatus;
	}

	public void setValidStatus(boolean validStatus) {
		this.validStatus = validStatus;
	}

	@Override
	public String toString() {
		return "ValidatingDTO [validStatus=" + validStatus + "]";
	}

}
