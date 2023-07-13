package com.nagarro.productCom.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
public class User
{
	@Id
	private String userEmail;
	private String userFirstName;
	private String userLastName;
	private String userPassword;
	
	@ManyToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinTable(name= "User_role", joinColumns= { @JoinColumn(name= "User_id")},inverseJoinColumns = { @JoinColumn(name="Role_id")})
	private Set<Role> role;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public User(String userEmail, String userFirstName, String userLastName, String userPassword, Set<Role> role) {
		super();
		this.userEmail = userEmail;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPassword = userPassword;
		this.role = role;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [userEmail=" + userEmail + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", userPassword=" + userPassword + ", Role=" + role + "]";
	}
	
	
	
	
}