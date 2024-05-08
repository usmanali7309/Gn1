package com.avaya.vcc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_skillset")
public class UserSkillset {
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;

	@Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "space_id")
    private String spaceId;

    @Column(name = "skill_id")
    private Integer skillId;

    @Column(name = "skill_name")
    private String skillName;

	public UserSkillset() {
		super();
	}

	public UserSkillset(Integer userId, String spaceId, Integer skillId, String skillName) {
		super();
//		this.id = id;Integer id, 
		this.userId = userId;
		this.spaceId = spaceId;
		this.skillId = skillId;
		this.skillName = skillName;
	}

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
    
    
    

}
