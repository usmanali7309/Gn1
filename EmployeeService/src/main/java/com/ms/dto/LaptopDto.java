package com.ms.dto;

public class LaptopDto {
	
	
	private long id;
	private String company;
	private String serialNumber;
	private String processorName;
    private int empId;
    
	public LaptopDto() {
		super();
	}

	public LaptopDto(long id, String company, String serialNumber, String processorName, int empId) {
		super();
		this.id = id;
		this.company = company;
		this.serialNumber = serialNumber;
		this.processorName = processorName;
		this.empId = empId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getProcessorName() {
		return processorName;
	}

	public void setProcessorName(String processorName) {
		this.processorName = processorName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}
    
    

}
