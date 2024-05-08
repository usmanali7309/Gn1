package com.avaya.vcc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaya.vcc.entities.ConfigSkillset;

@Repository
public interface ConfigSkillsetRepository extends JpaRepository<ConfigSkillset, Integer> {
	
	public List<ConfigSkillset> findByStatus(String status);
	
	public boolean existsBySkillName(String skillName);
	
	public ConfigSkillset findBySkillName(String skillName);

}
