package com.oa.staff.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import com.oa.authority.entity.Module;
import com.oa.authority.entity.Role;
import com.oa.staff.entity.UserInfornation;

public class UserRoleDTO {
	
	private String userId;
	private String userName;
	private String realName;
	
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
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
     * 动态生成where语句
     */
	public static Specification<UserInfornation> getWhereClause(UserRoleDTO userDTO) {
		return new Specification<UserInfornation>() {
			public Predicate toPredicate(Root<UserInfornation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 //1.声明Predicate集合
				 List<Predicate> predicate = new ArrayList<>();
				 //2.根据查询条件动态添加Predicate
				 if(userDTO.getRoleLevel()!=null) {
					 predicate.add(cb.lessThan(root.get("role").get("roleLevel").as(Integer.class),userDTO.getRoleLevel()));
				 }
				 if(userDTO.getUserName()!=null && !"".equals(userDTO.getUserName())) {
					 predicate.add(cb.like(root.get("userName").as(String.class),"%"+ userDTO.getUserName()+"%"));
				 }
				 if(userDTO.getRealName()!=null && !"".equals(userDTO.getRealName())) {
					 predicate.add(cb.like(root.get("realName").as(String.class),"%"+ userDTO.getRealName()+"%"));
				 }
				 if(userDTO.getRoleName()!=null && !"".equals(userDTO.getRoleName())) {
					 predicate.add(cb.like(root.get("role").get("roleName").as(String.class),"%"+ userDTO.getRoleName()+"%"));
				 }
				 //3.根据Predicate集合生成并返回and 连接的 where条件
	             return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			
			}
		};
	}
}
