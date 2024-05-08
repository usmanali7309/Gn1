package com.ms.laptop.service;

import org.springframework.stereotype.Service;

import com.ms.laptop.dto.LaptopDto;

@Service
public interface LaptopService {
	
	public LaptopDto createLaptop(LaptopDto laptopDto);
	
	public LaptopDto getLaptop(int empId);

}
