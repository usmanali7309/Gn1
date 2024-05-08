package com.ms.clients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ms.dto.LaptopDto;

@FeignClient(value = "LAPTOP-SERVICE",url = "http://localhost:7072")
//@LoadBalancerClient(name = "EMPLOYEE-SERVICE")
public interface ApiClientLaptop {
	
	@LoadBalanced
	@GetMapping(value="/api/laptop/{empId}")
	public LaptopDto getLaptop(@PathVariable("empId") long empId);
	
	@LoadBalanced
	@PostMapping(value="/api/laptop")
	public LaptopDto addLaptop(@RequestBody LaptopDto laptopDto);

}
