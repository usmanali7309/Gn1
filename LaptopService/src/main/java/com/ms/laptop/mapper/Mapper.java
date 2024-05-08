package com.ms.laptop.mapper;

import com.ms.laptop.dto.LaptopDto;
import com.ms.laptop.entities.Laptop;

public class Mapper {
	
	public static LaptopDto EntityToDto(Laptop laptop) {
		
		LaptopDto laptopDto = new LaptopDto();
		
		laptopDto.setId(laptop.getId());
		laptopDto.setCompany(laptop.getCompany());
		laptopDto.setSerialNumber(laptop.getSerialNumber());
		laptopDto.setProcessorName(laptop.getProcessorName());
		laptopDto.setEmpId(laptop.getEmpId());
		
		
		return laptopDto; 
	}
	
   public static Laptop DtoToEntity(LaptopDto laptopDto) {
		
		Laptop laptop = new Laptop();
		
//		laptop.setId(laptopDto.getId());
		laptop.setCompany(laptopDto.getCompany());
		laptop.setSerialNumber(laptopDto.getSerialNumber());
		laptop.setProcessorName(laptopDto.getProcessorName());
		laptop.setEmpId(laptopDto.getEmpId());
		
		return laptop; 
	}

}
