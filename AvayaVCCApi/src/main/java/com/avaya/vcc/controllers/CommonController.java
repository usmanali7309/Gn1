package com.avaya.vcc.controllers;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avaya.vcc.dtos.ConfigSkillsetDto;
import com.avaya.vcc.dtos.CustomResponse;
import com.avaya.vcc.dtos.UserListDto;
import com.avaya.vcc.entities.ConfigSkillset;
import com.avaya.vcc.entities.UserDetails;
import com.avaya.vcc.mappers.Mapper;
import com.avaya.vcc.repositories.ConfigSkillsetRepository;
import com.avaya.vcc.repositories.UserDetailsRepository;
import com.avaya.vcc.services.ConfigSkillsetService;
import com.avaya.vcc.services.UserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/CommonController/api")
public class CommonController {
	
	@Autowired
	private ConfigSkillsetService configSkillsetService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@GetMapping("/skillset_list")
	public ResponseEntity<?> skillset_list(){
		
		String status="Active";
		List<ConfigSkillset> skillStatus = configSkillsetService.findByStatus(status);
		
		ConfigSkillsetDto configSkillsetDto = new ConfigSkillsetDto();
//		configSkillsetDto.setStatus_Description("null");
		
		List<ConfigSkillsetDto> list = skillStatus.stream().map(e->Mapper.skillset_listDto(e)).collect(Collectors.toList());
			
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	@PostMapping("/create_skillset")
	public ResponseEntity<?> create_skillset(@RequestParam("skillname") String skillName){
		
		if(configSkillsetService.existsBySkillName(skillName))
		{		
			CustomResponse response = new CustomResponse();
			response.setStatus("Failed");
			response.setStatus_Description("Skill Name already exist");
			
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			
		}
		
		  configSkillsetService.saveConfigSkillset(skillName);
		
		  CustomResponse response = new CustomResponse();
		  response.setStatus("Success");
		  response.setStatus_Description("");
		
		  return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}
	
	
	
	@GetMapping("/active_delete_userlist")
	public ResponseEntity<?> active_delete_userlist(@RequestParam("emailid") String emailId){
		
		  userDetailsService.updateUserDetailsStatus(emailId);
		
		  CustomResponse response = new CustomResponse();
		  response.setStatus("Success");
		  response.setStatus_Description("Successfully Deleted");
		
		  return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/update_skillset")
	public ResponseEntity<?> update_skillset(@RequestParam("skillname") String skillName ,@RequestParam("id") String id){
		
		
		if(configSkillsetService.existsBySkillName(skillName))
		{		
			CustomResponse response = new CustomResponse();
			response.setStatus("Failed");
			response.setStatus_Description("Skill Name already exist");
			
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			
		}
		
		else {
			
			configSkillsetService.update_skillset(skillName, id);
			CustomResponse response = new CustomResponse();
			response.setStatus("Success");
			response.setStatus_Description("updated Successfully");
			
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
				
	}
	
	
	
	@GetMapping("/delete_skillset")
	public ResponseEntity<?> delete_skillset(@RequestParam("skillname") String skillName){
		
		   configSkillsetService.delete_skillset(skillName);
		   CustomResponse response = new CustomResponse();
		   response.setStatus("Success");
		   response.setStatus_Description("Successfully Deleted");
		
		   return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
		
	
	   @GetMapping("/delete_userlist")
	   public ResponseEntity<?> delete_userlist(@RequestParam("emailid") String emailid){
		
		      userDetailsService.inactiveUserDetailsStatus(emailid);
			
			  CustomResponse response = new CustomResponse();
			  response.setStatus("Success");
			  response.setStatus_Description("Successfully Deleted");
			
			  return new ResponseEntity<>(response,HttpStatus.OK);
		   
	    }
	   
	   
	   
	   @GetMapping("/update_userlist")
	   public ResponseEntity<?> update_userlist(@RequestParam("role") String role, @RequestParam("emailid") String emailid, @RequestParam("skill_id") String skill_id, @RequestParam("space_id") String space_id){
		
		   return null;
		   
	    }
	   
	   
	   @GetMapping("/user_login")
	   public ResponseEntity<?> user_login(@RequestParam("emailid") String email_id){
		
		   return null;
		   
	    }
	   
	   
	   @GetMapping("/user_list")
	   public ResponseEntity<?> user_list(){
		   
//		   @RequestParam("skillid") int skillid
//		   List<UserDetails> bySkillId = userDetailsRepository.findBySkillId(skillid);
		   
		   List<Object[]> userDetailsWithSkills = userDetailsRepository.getUserDetailsWithSkills();
		  	ObjectMapper mapper = new ObjectMapper();
		  	mapper.readValues(userDetailsWithSkills, UserListDto.class);
		
		   return new ResponseEntity<>(userDetailsWithSkills,HttpStatus.OK);
		   
	    }
	   
	
	
	
	
	

}
