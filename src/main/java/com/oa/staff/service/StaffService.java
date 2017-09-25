package com.oa.staff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.staff.dao.IStaffDao;
import com.oa.staff.entity.UserInfornation;

@Service
public class StaffService implements IStaffService {

	@Autowired
	private IStaffDao staffDao;
	
	@Override
	public UserInfornation findByUserName(String userName) {
		return staffDao.findByUserName(userName);
	}

}
