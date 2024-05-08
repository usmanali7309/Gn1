package com.avaya.vcc.services;

import org.springframework.stereotype.Service;

import com.avaya.vcc.entities.UserDetails;

@Service
public interface UserDetailsService {
	
	public void updateUserDetailsStatus(String emailId);
	
	public void inactiveUserDetailsStatus(String emailId);
	

}
