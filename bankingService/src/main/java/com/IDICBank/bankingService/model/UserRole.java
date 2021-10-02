package com.IDICBank.bankingService.model;

import javax.persistence.*;

@Entity
@Table(name="users_roles")
public class UserRole {
	
	@EmbeddedId 
    @Column(name="user_role_id")
	private Integer userroleid;
	
	@ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
	private Users userid;
	
	@ManyToOne
    @MapsId("Role_id")
    @JoinColumn(name = "Role_id")
	private Roles roleid;

	public Integer getUserroleid() {
		return userroleid;
	}

	public void setUserroleid(Integer userroleid) {
		this.userroleid = userroleid;
	}

	public Users getUserid() {
		return userid;
	}

	public void setUserid(Users userid) {
		this.userid = userid;
	}

	public Roles getRoleid() {
		return roleid;
	}

	public void setRoleid(Roles roleid) {
		this.roleid = roleid;
	}	

	
}
