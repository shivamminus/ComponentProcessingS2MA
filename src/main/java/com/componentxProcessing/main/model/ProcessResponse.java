package com.componentxProcessing.main.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "ReturnOrderResponse")

@Component
public class ProcessResponse {

	@Id
	@JsonProperty
	private String requestId;
	@JsonProperty
	private Integer processingCharge;
	@JsonProperty
	private Double packagingAndDeliveryCharge;
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String dateOfDelivery;

	public ProcessResponse() {
		super();
	}

	public ProcessResponse(String requestId, Integer processingCharge, Double packagingAndDeliveryCharge,
			String dateOfDelivery) {
		super();
		this.requestId = requestId;
		this.processingCharge = processingCharge;
		this.packagingAndDeliveryCharge = packagingAndDeliveryCharge;
		this.dateOfDelivery = dateOfDelivery;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Integer getProcessingCharge() {
		return processingCharge;
	}

	public void setProcessingCharge(Integer processingCharge) {
		this.processingCharge = processingCharge;
	}

	public Double getPackagingAndDeliveryCharge() {
		return packagingAndDeliveryCharge;
	}

	public void setPackagingAndDeliveryCharge(Double packagingAndDeliveryCharge) {
		this.packagingAndDeliveryCharge = packagingAndDeliveryCharge;
	}

	public String getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(String dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	@Override
	public String toString() {
		return "ProcessResponse [requestId=" + requestId + ", processingCharge=" + processingCharge
				+ ", packagingAndDeliveryCharge=" + packagingAndDeliveryCharge + ", dateOfDelivery=" + dateOfDelivery
				+ "]";
	}

	
}
