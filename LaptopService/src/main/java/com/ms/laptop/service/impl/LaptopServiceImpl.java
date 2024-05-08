package com.ms.laptop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.laptop.dto.LaptopDto;
import com.ms.laptop.entities.Laptop;
import com.ms.laptop.mapper.Mapper;
import com.ms.laptop.repositories.LaptopRepository;
import com.ms.laptop.service.LaptopService;

@Service
public class LaptopServiceImpl implements LaptopService {
	
	@Autowired
	private LaptopRepository laptopRepository;

	@Override
	public LaptopDto createLaptop(LaptopDto laptopDto) {
		
		Laptop laptop = Mapper.DtoToEntity(laptopDto);
		Laptop saved = laptopRepository.save(laptop);
		
		return Mapper.EntityToDto(saved);
	}

	@Override
	public LaptopDto getLaptop(int empId) {
		
		Laptop laptop = laptopRepository.findByEmpId(empId).get();
		return Mapper.EntityToDto(laptop);
	}

}
