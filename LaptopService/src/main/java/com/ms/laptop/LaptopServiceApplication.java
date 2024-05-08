package com.ms.laptop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@LoadBalancerClient(name = "LAPTOP-SERVICE")
public class LaptopServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaptopServiceApplication.class, args);
	}

}
