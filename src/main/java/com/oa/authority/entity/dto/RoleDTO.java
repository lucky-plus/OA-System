package com.oa.authority.entity.dto;


public class RoleDTO {
	
	private Integer roleId;
	private String roleName;
	private Integer roleLevel;
	private String modulesText;
	
	public static void entityToDto() {
		
	}
	
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}
	public String getModulesText() {
		return modulesText;
	}
	public void setModulesText(String modulesText) {
		this.modulesText = modulesText;
	}
	
}
