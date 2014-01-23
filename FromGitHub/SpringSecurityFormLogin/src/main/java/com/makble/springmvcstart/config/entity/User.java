package com.makble.springmvcstart.config.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table( name = "user_table" )
public class User {
	@NotEmpty
	private String username;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Role> roles = new ArrayList<Role>();
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Long getId() {
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
	public void setId(Long id) {
		this.id = id;
	}
	private String password;
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	
	
}