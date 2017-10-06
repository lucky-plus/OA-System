package com.oa.staff.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oa.staff.entity.UserInfornation;


public interface IStaffService {

	public UserInfornation findByUserName(String userName);
	public Page<UserInfornation> findAll(Pageable pageable);
	
}
