package com.avaya.vcc.dtos;

public class CustomResponse {
	
	private String Status;	
	private String Status_Description;

	public CustomResponse() {
		super();
	}

	public CustomResponse(String status, String status_Description) {
		super();
		Status = status;
		Status_Description = status_Description;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getStatus_Description() {
		return Status_Description;
	}

	public void setStatus_Description(String status_Description) {
		Status_Description = status_Description;
	}
	
	
	

}
