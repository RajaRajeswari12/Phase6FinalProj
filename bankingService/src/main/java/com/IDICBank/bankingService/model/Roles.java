package com.IDICBank.bankingService.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Roles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false,unique = true)
	private String roleName;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
	private List<Users> users;

	public Roles(int id, String roleName, List<Users> users) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.users = users;
	}

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", roleName=" + roleName + ", users=" + users + "]";
	}
	
	

}
