package com.avaya.vcc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agents")
public class Agent {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "space_id")
    private String spaceId;

    @Column(name = "status")
    private String status;

    @Column(name = "state")
    private String state;

    @Column(name = "max_no_answer")
    private Integer maxNoAnswer;

    @Column(name = "wrap_up_time")
    private Integer wrapUpTime;

    @Column(name = "reject_delay_time")
    private Integer rejectDelayTime;

    @Column(name = "busy_delay_time")
    private Integer busyDelayTime;

    @Column(name = "no_answer_delay_time")
    private Integer noAnswerDelayTime;

    @Column(name = "last_bridge_start")
    private Integer lastBridgeStart;

    @Column(name = "last_bridge_end")
    private Integer lastBridgeEnd;

    @Column(name = "last_offered_call")
    private Integer lastOfferedCall;

    @Column(name = "last_status_change")
    private Integer lastStatusChange;

    @Column(name = "no_answer_count")
    private Integer noAnswerCount;

    @Column(name = "calls_answered")
    private Integer callsAnswered;

    @Column(name = "talk_time")
    private Integer talkTime;

    @Column(name = "ready_time")
    private Integer readyTime;

    @Column(name = "external_calls_count")
    private Integer externalCallsCount;

	public Agent() {
		super();
	}

	public Agent(Integer id, Integer userId, String spaceId, String status, String state, Integer maxNoAnswer,
			Integer wrapUpTime, Integer rejectDelayTime, Integer busyDelayTime, Integer noAnswerDelayTime,
			Integer lastBridgeStart, Integer lastBridgeEnd, Integer lastOfferedCall, Integer lastStatusChange,
			Integer noAnswerCount, Integer callsAnswered, Integer talkTime, Integer readyTime,
			Integer externalCallsCount) {
		super();
		this.id = id;
		this.userId = userId;
		this.spaceId = spaceId;
		this.status = status;
		this.state = state;
		this.maxNoAnswer = maxNoAnswer;
		this.wrapUpTime = wrapUpTime;
		this.rejectDelayTime = rejectDelayTime;
		this.busyDelayTime = busyDelayTime;
		this.noAnswerDelayTime = noAnswerDelayTime;
		this.lastBridgeStart = lastBridgeStart;
		this.lastBridgeEnd = lastBridgeEnd;
		this.lastOfferedCall = lastOfferedCall;
		this.lastStatusChange = lastStatusChange;
		this.noAnswerCount = noAnswerCount;
		this.callsAnswered = callsAnswered;
		this.talkTime = talkTime;
		this.readyTime = readyTime;
		this.externalCallsCount = externalCallsCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getMaxNoAnswer() {
		return maxNoAnswer;
	}

	public void setMaxNoAnswer(Integer maxNoAnswer) {
		this.maxNoAnswer = maxNoAnswer;
	}

	public Integer getWrapUpTime() {
		return wrapUpTime;
	}

	public void setWrapUpTime(Integer wrapUpTime) {
		this.wrapUpTime = wrapUpTime;
	}

	public Integer getRejectDelayTime() {
		return rejectDelayTime;
	}

	public void setRejectDelayTime(Integer rejectDelayTime) {
		this.rejectDelayTime = rejectDelayTime;
	}

	public Integer getBusyDelayTime() {
		return busyDelayTime;
	}

	public void setBusyDelayTime(Integer busyDelayTime) {
		this.busyDelayTime = busyDelayTime;
	}

	public Integer getNoAnswerDelayTime() {
		return noAnswerDelayTime;
	}

	public void setNoAnswerDelayTime(Integer noAnswerDelayTime) {
		this.noAnswerDelayTime = noAnswerDelayTime;
	}

	public Integer getLastBridgeStart() {
		return lastBridgeStart;
	}

	public void setLastBridgeStart(Integer lastBridgeStart) {
		this.lastBridgeStart = lastBridgeStart;
	}

	public Integer getLastBridgeEnd() {
		return lastBridgeEnd;
	}

	public void setLastBridgeEnd(Integer lastBridgeEnd) {
		this.lastBridgeEnd = lastBridgeEnd;
	}

	public Integer getLastOfferedCall() {
		return lastOfferedCall;
	}

	public void setLastOfferedCall(Integer lastOfferedCall) {
		this.lastOfferedCall = lastOfferedCall;
	}

	public Integer getLastStatusChange() {
		return lastStatusChange;
	}

	public void setLastStatusChange(Integer lastStatusChange) {
		this.lastStatusChange = lastStatusChange;
	}

	public Integer getNoAnswerCount() {
		return noAnswerCount;
	}

	public void setNoAnswerCount(Integer noAnswerCount) {
		this.noAnswerCount = noAnswerCount;
	}

	public Integer getCallsAnswered() {
		return callsAnswered;
	}

	public void setCallsAnswered(Integer callsAnswered) {
		this.callsAnswered = callsAnswered;
	}

	public Integer getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(Integer talkTime) {
		this.talkTime = talkTime;
	}

	public Integer getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(Integer readyTime) {
		this.readyTime = readyTime;
	}

	public Integer getExternalCallsCount() {
		return externalCallsCount;
	}

	public void setExternalCallsCount(Integer externalCallsCount) {
		this.externalCallsCount = externalCallsCount;
	}
    	
	

}
