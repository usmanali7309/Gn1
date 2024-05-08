package com.avaya.vcc.entities;

import java.sql.Time;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_agent_state")
public class UserAgentState {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reference_id")
    private Integer referenceId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_status")
    private String userStatus;

    @Column(name = "not_ready_reason")
    private String notReadyReason;

    @Column(name = "event_time")
    private LocalDateTime eventTime;

    @Column(name = "state_changed_time")
    private LocalDateTime stateChangedTime;

    @Column(name = "state_duration")
    private Time stateDuration;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UserDetails userDetails;

	public UserAgentState() {
		super();
	}

	public UserAgentState(Integer referenceId, Integer userId, String userStatus, String notReadyReason,
			LocalDateTime eventTime, LocalDateTime stateChangedTime, Time stateDuration, String remarks,
			UserDetails userDetails) {
		super();
		this.referenceId = referenceId;
		this.userId = userId;
		this.userStatus = userStatus;
		this.notReadyReason = notReadyReason;
		this.eventTime = eventTime;
		this.stateChangedTime = stateChangedTime;
		this.stateDuration = stateDuration;
		this.remarks = remarks;
		this.userDetails = userDetails;
	}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getNotReadyReason() {
		return notReadyReason;
	}

	public void setNotReadyReason(String notReadyReason) {
		this.notReadyReason = notReadyReason;
	}

	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalDateTime eventTime) {
		this.eventTime = eventTime;
	}

	public LocalDateTime getStateChangedTime() {
		return stateChangedTime;
	}

	public void setStateChangedTime(LocalDateTime stateChangedTime) {
		this.stateChangedTime = stateChangedTime;
	}

	public Time getStateDuration() {
		return stateDuration;
	}

	public void setStateDuration(Time stateDuration) {
		this.stateDuration = stateDuration;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	
    
    

}
