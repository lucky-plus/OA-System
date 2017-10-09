package com.oa.staff.entity.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.oa.authority.entity.Module;
import com.oa.authority.entity.Role;
import com.oa.staff.entity.UserInfornation;

public class UserRoleDTO {
	
	private String userId;
	private String userName;
	
	private Integer roleId;
	private String roleName;
	private Integer roleLevel;
	private String modulesText;
	
	public static void userToUserRole(UserRoleDTO dto, UserInfornation user) {
		BeanUtils.copyProperties(user, dto);
	}
	
	public static void roleToUserRole(UserRoleDTO dto, Role entity) {
		
		BeanUtils.copyProperties(entity, dto);
		List<Module> list = entity.getModules();
		StringBuffer str = new StringBuffer();
		
		for(Module module : list) {
			str.append(module.getModelName()+"、");
		}
		dto.setModulesText(str.toString());
		
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
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
