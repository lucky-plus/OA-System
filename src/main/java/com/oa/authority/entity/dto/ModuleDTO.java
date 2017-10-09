package com.oa.authority.entity.dto;

import org.springframework.beans.BeanUtils;

import com.oa.authority.entity.Module;

public class ModuleDTO {
	
	private Integer moduleId;
	private String parentId;
	private String modelName;
	private String parentName;
	private String creatBy;
	
	public static void dtoToEntity(ModuleDTO dto, Module entity) {
		BeanUtils.copyProperties(dto, entity);
	}
	
	public static void entityToDto(ModuleDTO dto, Module entity) {
		BeanUtils.copyProperties(entity, dto);
	}
	
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getCreatBy() {
		return creatBy;
	}
	public void setCreatBy(String creatBy) {
		this.creatBy = creatBy;
	}
	
}
