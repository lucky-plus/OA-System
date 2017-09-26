package com.oa.staff.service;

import com.oa.staff.entity.UserInfornation;

public interface IStaffService {

	public UserInfornation findByUserName(String userName);
	
}
