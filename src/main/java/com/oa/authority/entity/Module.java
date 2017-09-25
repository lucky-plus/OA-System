package com.oa.authority.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="t_module")
public class Module {
	
	private Integer moduleId;
	private String parentId;
	private String modelName;
	private String parentName;
	private String creatBy;
	private List<Role> roles = new ArrayList<Role>();

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getModuleId() {
		return moduleId;
	}
	public String getParentId() {
		return parentId;
	}
	public String getModelName() {
		return modelName;
	}
	public String getParentName() {
		return parentName;
	}
	public String getCreatBy() {
		return creatBy;
	}
	@ManyToMany(mappedBy="modules")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	public List<Role> getRoles() {
		return roles;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public void setCreatBy(String creatBy) {
		this.creatBy = creatBy;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
