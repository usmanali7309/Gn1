package com.avaya.vcc.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cdr")
public class Cdr {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_id")
    private Integer refId;

    @Column(name = "uuid", unique = true)
    private UUID uuid;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_skillset_id")
    private String customerSkillsetId;

    @Column(name = "customer_skillset_name")
    private String customerSkillsetName;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "skill_joined")
    private LocalDateTime skillJoined;

    @Column(name = "answered")
    private LocalDateTime answered;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "agent_id")
    private Integer agentId;

    @Column(name = "agent_space_id")
    private String agentSpaceId;

    @Column(name = "meeting_id")
    private String meetingId;

	public Cdr() {
		super();
	}

	public Cdr(Integer refId, UUID uuid, String customerName, String customerEmail, String customerPhone,
			String customerSkillsetId, String customerSkillsetName, LocalDateTime startTime, LocalDateTime skillJoined,
			LocalDateTime answered, LocalDateTime endTime, Integer agentId, String agentSpaceId, String meetingId) {
		super();
		this.refId = refId;
		this.uuid = uuid;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.customerSkillsetId = customerSkillsetId;
		this.customerSkillsetName = customerSkillsetName;
		this.startTime = startTime;
		this.skillJoined = skillJoined;
		this.answered = answered;
		this.endTime = endTime;
		this.agentId = agentId;
		this.agentSpaceId = agentSpaceId;
		this.meetingId = meetingId;
	}

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerSkillsetId() {
		return customerSkillsetId;
	}

	public void setCustomerSkillsetId(String customerSkillsetId) {
		this.customerSkillsetId = customerSkillsetId;
	}

	public String getCustomerSkillsetName() {
		return customerSkillsetName;
	}

	public void setCustomerSkillsetName(String customerSkillsetName) {
		this.customerSkillsetName = customerSkillsetName;
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

	public LocalDateTime getAnswered() {
		return answered;
	}

	public void setAnswered(LocalDateTime answered) {
		this.answered = answered;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getAgentSpaceId() {
		return agentSpaceId;
	}

	public void setAgentSpaceId(String agentSpaceId) {
		this.agentSpaceId = agentSpaceId;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
    
    
	

}
