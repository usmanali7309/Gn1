package com.avaya.vcc.dtos;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class ConfigSkillsetDto {
	
	
	
    private Integer skillId;

    
    private String skillName;

    
    private LocalDateTime createdDate;

   
    private String createdBy;

   
    private String status;
    
    private String Status_Description;

	public ConfigSkillsetDto() {
		super();
	}

	public ConfigSkillsetDto(Integer skillId, String skillName, LocalDateTime createdDate, String createdBy,
			String status, String status_Description) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.status = status;
		Status_Description = status_Description;
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_Description() {
		return Status_Description;
	}

	public void setStatus_Description(String status_Description) {
		Status_Description = status_Description;
	}

	
    
    

}
