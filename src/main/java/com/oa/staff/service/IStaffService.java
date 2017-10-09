package com.oa.staff.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.oa.message.entity.Notice;
import com.oa.message.entity.dto.NoticeDTO;
import com.oa.staff.entity.UserInfornation;
import com.oa.staff.entity.dto.UserRoleDTO;

public interface IStaffService {

	public UserInfornation findByUserName(String userName);
	public Page<UserInfornation> findAll(Pageable pageable);
	public Page<UserInfornation> findAll(Specification<UserInfornation> spec, Pageable pageable);
	
	public Page<UserRoleDTO> findUserRole(Integer roleLevel, Pageable pageable); 
	
}
