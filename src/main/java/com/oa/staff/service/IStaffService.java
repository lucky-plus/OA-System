package com.oa.staff.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oa.staff.entity.UserInfornation;
import com.oa.staff.entity.dto.UserRoleDTO;

public interface IStaffService {

	public UserInfornation findByUserName(String userName);
	
	public Page<UserRoleDTO> findUserRole(Integer roleLevel, Pageable pageable); 
	
}
