package com.oa.authority.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="t_role")
public class Role {
	
	private Integer roleId;
	private String roleName;
	private Integer roleLevel;
	private List<Module> modules = new ArrayList<Module>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getRoleId() {
		return roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="t_role_module",
		joinColumns=@JoinColumn(name="roleId"),
		inverseJoinColumns=@JoinColumn(name="moduleId")
	)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	public List<Module> getModules() {
		return modules;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
}
