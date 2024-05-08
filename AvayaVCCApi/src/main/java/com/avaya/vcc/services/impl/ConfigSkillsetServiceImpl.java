package com.avaya.vcc.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaya.vcc.entities.ConfigSkillset;
import com.avaya.vcc.repositories.ConfigSkillsetRepository;
import com.avaya.vcc.services.ConfigSkillsetService;

@Service
public class ConfigSkillsetServiceImpl implements ConfigSkillsetService {
	
	@Autowired
	private ConfigSkillsetRepository configSkillsetRepository;

	@Override
	public List<ConfigSkillset> findByStatus(String status) {
		
		List<ConfigSkillset> skillStatus = configSkillsetRepository.findByStatus(status);
		return skillStatus;
	}

	@Override
	public boolean existsBySkillName(String skillName) {
		
		if(configSkillsetRepository.existsBySkillName(skillName)) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public void saveConfigSkillset(String skillName) {
		
		ConfigSkillset configSkillset = new ConfigSkillset();
		
		configSkillset.setSkillName(skillName);
		LocalDateTime localDateTime = LocalDateTime.now();
		configSkillset.setCreatedDate(localDateTime);
		configSkillset.setStatus("Active");
		
		configSkillsetRepository.save(configSkillset);
		
	}

	@Override
	public void update_skillset(String skillName, String id) {
		
		  int skillId = Integer.parseInt(id);
		  ConfigSkillset configSkillset = configSkillsetRepository.findById(skillId).get();
		  configSkillset.setSkillName(skillName);	
		  configSkillsetRepository.save(configSkillset);
		
	}

	@Override
	public void delete_skillset(String skillName) {
		
		ConfigSkillset bySkillName = configSkillsetRepository.findBySkillName(skillName);
		
		bySkillName.setStatus("Inactive");
		configSkillsetRepository.save(bySkillName);
		
	}
	
	
	

}
