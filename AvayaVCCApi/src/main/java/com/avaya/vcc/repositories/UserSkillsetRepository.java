package com.avaya.vcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaya.vcc.entities.UserSkillset;

@Repository
public interface UserSkillsetRepository extends JpaRepository<UserSkillset, Integer> {
	
	

}
