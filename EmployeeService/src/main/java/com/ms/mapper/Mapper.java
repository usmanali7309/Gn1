package com.ms.mapper;

import com.ms.dto.EmployeeDto;
import com.ms.entities.Employee;

public class Mapper {
	
	
	public static EmployeeDto EntityToDto(Employee emp) {
		
		EmployeeDto empDto = new EmployeeDto();
	    empDto.setEmpId(emp.getEmpId());
	    empDto.setEmployeeName(emp.getEmployeeName());
	    empDto.setEmail(emp.getEmail());
	    empDto.setPhone(emp.getPhone());
	    empDto.setAddress(emp.getAddress());
		
		return empDto;
	
	}
	
public static Employee DtoToEntity(EmployeeDto empDto) {
		
		Employee emp = new Employee();
//	    emp.setEmpId(empDto.getEmpId());
	    emp.setEmployeeName(empDto.getEmployeeName());
	    emp.setEmail(empDto.getEmail());
	    emp.setPhone(empDto.getPhone());
	    emp.setAddress(empDto.getAddress());
		
		return emp;
	
	}

}
