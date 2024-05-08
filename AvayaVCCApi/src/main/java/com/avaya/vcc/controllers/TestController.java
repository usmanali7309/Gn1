package com.avaya.vcc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avaya.vcc.entities.TestData;
import com.avaya.vcc.repositories.TestDataRepository;

@RestController
@RequestMapping("/TestController/api")
public class TestController {
	
	@Value("${API_KEY}")
	private String API_KEY;
	
	@Autowired
	private TestDataRepository testDataRepository;
	
	@GetMapping("/getData")
	public ResponseEntity<?> getTestData(){
		
		List<TestData> all = testDataRepository.findAll();
		
		return new ResponseEntity<>(all,HttpStatus.OK);
	}
	
	@GetMapping("/example")
    public ResponseEntity<String> exampleEndpoint(@RequestHeader("api-key") String apiKey) {
		
		System.out.println("API_KEY>> "+API_KEY);
        if (!apiKey.equals(API_KEY)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API key");
        }
        return ResponseEntity.ok("Endpoint accessed successfully");
    }
	

}
