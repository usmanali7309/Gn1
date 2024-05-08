package com.avaya.vcc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaya.vcc.entities.UserDetails;
import com.avaya.vcc.repositories.UserDetailsRepository;
import com.avaya.vcc.services.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired 
	private UserDetailsRepository userDetailsRepository;

	@Override
	public void updateUserDetailsStatus(String emailId) {
		UserDetails UserDetailsByEmailId = userDetailsRepository.findByEmailId(emailId);
		UserDetailsByEmailId.setStatus("Active");
		userDetailsRepository.save(UserDetailsByEmailId);
		
	}

	@Override
	public void inactiveUserDetailsStatus(String emailId) {
		UserDetails UserDetailsByEmailId = userDetailsRepository.findByEmailId(emailId);
		UserDetailsByEmailId.setStatus("Inactive");
		userDetailsRepository.save(UserDetailsByEmailId);
		
	}



}
