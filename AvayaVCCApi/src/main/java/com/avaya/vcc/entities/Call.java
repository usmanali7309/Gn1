package com.avaya.vcc.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calls")
public class Call {
	
	
	@Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "skill_joined")
    private LocalDateTime skillJoined;

    @Column(name = "skill_id")
    private String skillId;

    @Column(name = "agent_id")
    private Integer agentId;

    @Column(name = "space_id")
    private String spaceId;

	public Call() {
		super();
	}

	public Call(String uuid, LocalDateTime startTime, LocalDateTime skillJoined, String skillId, Integer agentId,
			String spaceId) {
		super();
		this.uuid = uuid;
		this.startTime = startTime;
		this.skillJoined = skillJoined;
		this.skillId = skillId;
		this.agentId = agentId;
		this.spaceId = spaceId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getSkillJoined() {
		return skillJoined;
	}

	public void setSkillJoined(LocalDateTime skillJoined) {
		this.skillJoined = skillJoined;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
    
    

}
