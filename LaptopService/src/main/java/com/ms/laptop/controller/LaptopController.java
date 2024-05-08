package com.ms.laptop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.laptop.dto.LaptopDto;
import com.ms.laptop.service.LaptopService;

@RestController
@RequestMapping("api/laptop")
public class LaptopController {
	
	@Autowired
	private LaptopService laptopService;
	
	@PostMapping
	public ResponseEntity<?> createLaptop(@RequestBody LaptopDto laptopDto){
		
		LaptopDto laptop = laptopService.createLaptop(laptopDto);
		
		return new ResponseEntity<>(laptop,HttpStatus.CREATED);
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity<?> getLaptop(@PathVariable("empId") int empId){
		
		LaptopDto laptop = laptopService.getLaptop(empId);
		
		return new ResponseEntity<>(laptop,HttpStatus.OK);
	}
	

}
