package com.avaya.vcc.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avaya.vcc.entities.ConfigSkillset;

@Service
public interface ConfigSkillsetService {
	
	public List<ConfigSkillset> findByStatus(String status);
	
	public boolean existsBySkillName(String skillName);
	
	public void saveConfigSkillset(String skillName);
	
	public void update_skillset(String skillName, String id);
	
	public void delete_skillset(String skillName);

}
