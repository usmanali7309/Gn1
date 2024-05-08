package com.avaya.vcc.mappers;

import com.avaya.vcc.dtos.ConfigSkillsetDto;
import com.avaya.vcc.entities.ConfigSkillset;

public class Mapper {
	
	
	public static ConfigSkillsetDto skillset_listDto(ConfigSkillset configSkillset) {
		
		ConfigSkillsetDto configSkillsetDto = new ConfigSkillsetDto();
		
		configSkillsetDto.setSkillId(configSkillset.getSkillId());
		configSkillsetDto.setSkillName(configSkillset.getSkillName());
		configSkillsetDto.setCreatedDate(configSkillset.getCreatedDate());
		configSkillsetDto.setCreatedBy(configSkillset.getCreatedBy());
		configSkillsetDto.setStatus(configSkillset.getStatus());
		
		return configSkillsetDto;
		
	}

}
