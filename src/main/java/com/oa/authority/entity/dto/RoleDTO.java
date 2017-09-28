package com.oa.authority.entity.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.oa.authority.entity.Module;
import com.oa.authority.entity.Role;

public class RoleDTO {
	
	private Integer roleId;
	private String roleName;
	private Integer roleLevel;
	private String modulesText;
	private Integer[] moduleIds;
	
	public static void dtoToEntity(RoleDTO dto, Role entity) {
		
		BeanUtils.copyProperties(dto, entity);
		
		List<Module> modules = new ArrayList<Module>();
		for(Integer moduleId : dto.getModuleIds()) {
			Module module = new Module();
			module.setModuleId(moduleId);
			modules.add(module);
		}
		if(modules.size() > 0) {
			entity.setModules(modules);
		}
		
	}

	public static void entityToDto(RoleDTO dto, Role entity) {
		
		BeanUtils.copyProperties(entity, dto);
		List<Module> list = entity.getModules();
		StringBuffer str = new StringBuffer();
		
		for(Module module : list) {
			str.append(module.getModelName()+"、");
		}
		dto.setModulesText(str.toString());
		
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
	public Integer[] getModuleIds() {
		return moduleIds;
	}
	public void setModuleIds(Integer[] moduleIds) {
		this.moduleIds = moduleIds;
	}
	
}
