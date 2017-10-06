package com.oa.staff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Page<UserInfornation> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return staffDao.findAll(pageable);
	}

}
