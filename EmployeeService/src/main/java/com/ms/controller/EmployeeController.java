package com.ms.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.dto.EmployeeDto;
import com.ms.dto.LaptopDto;
import com.ms.services.EmployeeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("api/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<?> createEmp(@RequestBody EmployeeDto empDto){
		EmployeeDto createEmp = employeeService.CreateEmp(empDto);
		
		return new ResponseEntity(createEmp, HttpStatus.CREATED);
	}
	
	@HystrixCommand(fallbackMethod = "callLaptopServiceAndGetDetails_Fallback")
	@GetMapping("/{empId}")
	public ResponseEntity<?> getEmp(@PathVariable("empId") long empId ){
		EmployeeDto emp = employeeService.getEmp(empId);
		
		return new ResponseEntity(emp, HttpStatus.OK);
	}

	
	@PostMapping("/addLaptop")
	public ResponseEntity<?> addLaptop(@RequestBody LaptopDto laptopDto){
		LaptopDto laptop = employeeService.addLaptop(laptopDto);
		
		return new ResponseEntity(laptop, HttpStatus.CREATED);
	}
	
	
	@SuppressWarnings("unused")
	private  ResponseEntity<?> callLaptopServiceAndGetDetails_Fallback(long id) {

		System.out.println("Laptop Service is down!!! fallback route enabled...");
		
		LocalDate date = LocalDate.now();

		EmployeeDto empDto = new EmployeeDto();
		 String msg = "Laptop Service is down!!! fallback route enabled..."+date;
		 
		return   new ResponseEntity<>(msg,HttpStatus.GATEWAY_TIMEOUT);
	}
	
}
