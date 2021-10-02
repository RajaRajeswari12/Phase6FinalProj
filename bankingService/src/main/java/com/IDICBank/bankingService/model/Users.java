package com.IDICBank.bankingService.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries(value= {
		@NamedQuery(name="getUserByUsername",query="select u from Users u where u.username=?1 ")
})
	

public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false,unique = true)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	private boolean enabled;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
			name="users_roles",
			joinColumns = {@JoinColumn(name="User_id",referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name="Role_id",referencedColumnName = "id")})
	private List<Roles> roles;
	



	public Users(Integer id, String username, String password, boolean enabled, List<Roles> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	
	

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled + "]";
	}
	
	
	

}
