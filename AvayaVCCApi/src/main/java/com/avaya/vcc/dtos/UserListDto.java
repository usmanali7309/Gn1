package com.avaya.vcc.dtos;

public class UserListDto {
	
	
	private int userId;
    private int spaceId;
    private String displayName;
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNo;
    private String role;
    private String status;
    private int skillId;
    private String skillName;
    
	public UserListDto() {
		super();
	}

	public UserListDto(int userId, int spaceId, String displayName, String firstName, String lastName, String emailId,
			String phoneNo, String role, String status, int skillId, String skillName) {
		super();
		this.userId = userId;
		this.spaceId = spaceId;
		this.displayName = displayName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.role = role;
		this.status = status;
		this.skillId = skillId;
		this.skillName = skillName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
    
    
    

}
