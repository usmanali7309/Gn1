package com.avaya.vcc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avaya.vcc.entities.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

	public UserDetails findByEmailId(String emailId);
	
//	For Testing
//	@Query("SELECT u FROM UserDetails u INNER JOIN ConfigSkillset us ON u.userId = us.skillId WHERE us.skillId = :skillId")
//    List<UserDetails> findBySkillId(@Param("skillId") Integer skillId);
	
	
    @Query("SELECT us.userId, us.spaceId, u.displayName, u.firstName, u.lastName, u.emailId, u.phoneNo, u.role, u.status, us.skillId, cs.skillName " +
            "FROM UserDetails u " +
            "INNER JOIN UserSkillset us ON u.userId = us.userId " +
            "INNER JOIN ConfigSkillset cs ON us.skillId = cs.skillId")
     List<Object[]> getUserDetailsWithSkills();
	
}
