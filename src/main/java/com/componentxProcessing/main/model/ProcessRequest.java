package com.componentxProcessing.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "ReturnOrderRequest")
public class ProcessRequest {

	@Id
	@Column(name = "UserName")
//	@JsonProperty
	private String userName;

	@Column(name = "ContactNumber")
//	@JsonProperty
	private Long contactNumber;

	@Column(name = "CreditCardNumber")
//	@JsonProperty
	private String creditCardNumber;

	@Column(name = "IsPriorityRequest")
//	@JsonProperty
	private Boolean isPriorityRequest;

	@Column(name = "ComponentType") // integral-repair, accessory-replace
//	@JsonProperty
	private String componentType;

	@Column(name = "ComponentName")
//	@JsonProperty
	private String componentName;

	@Column(name = "Quantity")
//	@JsonProperty
	private Integer quantity;

	public ProcessRequest() {
		super();
	}

	public ProcessRequest(String userName, Long contactNumber, String creditCardNumber, Boolean isPriorityRequest,
			String componentType, String componentName, Integer quantity) {
		super();
		this.userName = userName;
		this.contactNumber = contactNumber;
		this.creditCardNumber = creditCardNumber;
		this.isPriorityRequest = isPriorityRequest;
		this.componentType = componentType;
		this.componentName = componentName;
		this.quantity = quantity;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public Boolean getIsPriorityRequest() {
		return isPriorityRequest;
	}

	public void setIsPriorityRequest(Boolean isPriorityRequest) {
		this.isPriorityRequest = isPriorityRequest;
	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProcessRequest [userName=" + userName + ", contactNumber=" + contactNumber + ", creditCardNumber="
				+ creditCardNumber + ", isPriorityRequest=" + isPriorityRequest + ", componentType=" + componentType
				+ ", componentName=" + componentName + ", quantity=" + quantity + "]";
	}

}
