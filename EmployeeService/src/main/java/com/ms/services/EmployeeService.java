package com.ms.services;

import org.springframework.stereotype.Service;

import com.ms.dto.EmployeeDto;
import com.ms.dto.LaptopDto;

@Service
public interface EmployeeService {
	
	public EmployeeDto CreateEmp(EmployeeDto empDto);
	
	public EmployeeDto getEmp(long id);
	
	public LaptopDto addLaptop(LaptopDto laptopDto);

}
