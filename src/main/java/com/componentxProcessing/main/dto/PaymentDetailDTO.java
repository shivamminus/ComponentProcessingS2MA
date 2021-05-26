package com.componentxProcessing.main.dto;

public class PaymentDetailDTO {
	private Integer creditLimit, processingCharge;
	private String creditCardNumber, requestId;
	public PaymentDetailDTO() {
		super();
	}
	public PaymentDetailDTO(String requestId, Integer creditLimit, Integer processingCharge, String creditCardNumber) {
		super();
		this.requestId = requestId;
		this.creditLimit = creditLimit;
		this.processingCharge = processingCharge;
		this.creditCardNumber = creditCardNumber;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public Integer getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(Integer creditLimit) {
		this.creditLimit = creditLimit;
	}
	public Integer getProcessingCharge() {
		return processingCharge;
	}
	public void setProcessingCharge(Integer processingCharge) {
		this.processingCharge = processingCharge;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
}
