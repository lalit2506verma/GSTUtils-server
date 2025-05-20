package com.GSTUtils.server.Model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long roleID;
	private String roleName;
	
	// Role can have many userRoles -> one role to many userRoles
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	private List<UserRole> userRoles = new ArrayList<>();
	
	public Role() {
	}
	
	public Role(Long roleID, String roleName) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
	}

	public Long getRoleID() {
		return roleID;
	}
	
	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
