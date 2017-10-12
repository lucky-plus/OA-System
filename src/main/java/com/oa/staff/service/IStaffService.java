package com.oa.staff.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


import com.oa.staff.entity.UserInfornation;
import com.oa.staff.entity.dto.UserPostDTO;
import com.oa.staff.entity.dto.UserRoleDTO;

public interface IStaffService {

	//CRUD
	public void save(UserPostDTO dto);
	public void delete(UserInfornation entity);
	public void delete(String id);
	public void delete(String[] ids);
	public UserInfornation findByUserName(String userName);
	public Page<UserPostDTO> findAll(Pageable pageable);
	public Page<UserPostDTO> findAll(Specification<UserInfornation> spec, Pageable pageable);
	
	public Page<UserRoleDTO> findUserRole(Integer roleLevel, Pageable pageable); 
	
	//修改用户权限
	public void userRoleUpdate(String userId, Integer roleId);
	
}
