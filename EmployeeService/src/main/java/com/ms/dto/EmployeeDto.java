package com.ms.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
	
    private long empId;
	
	private String employeeName;
	private String email;
	private String phone;
	private String address;
	
	private LaptopDto laptop;
		
		
	public EmployeeDto() {
		super();
	}
	
	public EmployeeDto(long empId, String employeeName, String email, String phone, String address,
			LaptopDto laptop) {
		super();
		this.empId = empId;
		this.employeeName = employeeName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.laptop = laptop;
	}
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LaptopDto getLaptop() {
		return laptop;
	}
	public void setLaptop(LaptopDto laptop) {
		this.laptop = laptop;
	}
	
	

}
