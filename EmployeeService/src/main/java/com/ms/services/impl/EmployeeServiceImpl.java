package com.ms.services.impl;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.clients.ApiClientLaptop;
import com.ms.dto.EmployeeDto;
import com.ms.dto.LaptopDto;
import com.ms.entities.Employee;
import com.ms.mapper.Mapper;
import com.ms.repositories.EmployeeRepository;
import com.ms.services.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ApiClientLaptop apiClientLaptop;
	
	@Override
	public EmployeeDto CreateEmp(EmployeeDto empDto) {
		Employee emp = Mapper.DtoToEntity(empDto);
		Employee saved = employeeRepository.save(emp);	
		
		return Mapper.EntityToDto(saved);
	}

//	@HystrixCommand(fallbackMethod = "callLaptopServiceAndGetDetails_Fallback")
	@LoadBalanced
	@Override
	public EmployeeDto getEmp(long id) {
		
		Employee employee = employeeRepository.findById(id).get();
		EmployeeDto entityToDto = Mapper.EntityToDto(employee);
		
//		RestTemplate rest = new RestTemplate();
//		ResponseEntity<LaptopDto> forEntity = rest.getForEntity("http://localhost:7072/api/laptop/"+id, LaptopDto.class);
//		LaptopDto laptop = forEntity.getBody();
		
		
		LaptopDto laptop = apiClientLaptop.getLaptop(id);
		
		System.out.println(laptop.getSerialNumber());
		
		entityToDto.setLaptop(laptop);
		
		return entityToDto ;
	}

	@Override
	public LaptopDto addLaptop(LaptopDto laptopDto) {
		
		LaptopDto laptop = apiClientLaptop.addLaptop(laptopDto);
		
		return laptop;
	}
	
//	@SuppressWarnings("unused")
//	private EmployeeDto callLaptopServiceAndGetDetails_Fallback(long id) {
//
//		System.out.println("Laptop Service is down!!! fallback route enabled...");
//		
//		LocalDate date = LocalDate.now();
//
//		EmployeeDto empDto = new EmployeeDto();
//		
//		return  empDto ;
//	}


}
