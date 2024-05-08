package com.avaya.vcc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TestData" ,schema="public")
public class TestData {
	
	@Id
	private int id;
	
	private String emp_name;
	
	private String email;
	
	private String phone_number;

	public TestData() {
		super();
	}

	public TestData(int id, String emp_name, String email, String phone_number) {
		super();
		this.id = id;
		this.emp_name = emp_name;
		this.email = email;
		this.phone_number = phone_number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	

}
