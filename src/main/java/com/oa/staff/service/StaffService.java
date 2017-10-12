package com.oa.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import com.oa.staff.dao.IStaffDao;
import com.oa.staff.entity.UserInfornation;
import com.oa.staff.entity.dto.UserRoleDTO;

@Service
public class StaffService implements IStaffService {

	@Autowired
	private IStaffDao staffDao;
	
	public void setStaffDao(IStaffDao staffDao) {
		this.staffDao = staffDao;
	}
	
	@Override
	public UserInfornation findByUserName(String userName) {
		return staffDao.findByUserName(userName);
	}

	@Override
	public Page<UserRoleDTO> findUserRole(Integer roleLevel, Pageable pageable) {
		Page<UserInfornation> userPage = staffDao.findUserRole(roleLevel, pageable);
		List<UserRoleDTO> dtoList = new ArrayList<UserRoleDTO>();
		for(UserInfornation user : userPage.getContent()) {
			UserRoleDTO dto = new UserRoleDTO();
			UserRoleDTO.userToUserRole(dto, user);
			UserRoleDTO.roleToUserRole(dto, user.getRole());
			dtoList.add(dto);
		}
		Page<UserRoleDTO> userRolePage = new PageImpl<UserRoleDTO>(dtoList, pageable, userPage.getTotalElements());
		return userRolePage;
	}

	@Override
	public Page<UserRoleDTO> findUserRoleByCondition(Specification<UserInfornation> spec, Pageable pageable) {
		Page<UserInfornation> userPage =  staffDao.findAll(spec,pageable);
		List<UserRoleDTO> dtoList = new ArrayList<UserRoleDTO>();
		for(UserInfornation user : userPage.getContent()) {
			UserRoleDTO dto = new UserRoleDTO();
			UserRoleDTO.userToUserRole(dto, user);
			UserRoleDTO.roleToUserRole(dto, user.getRole());
			dtoList.add(dto);
		}
		Page<UserRoleDTO> userRolePage = new PageImpl<UserRoleDTO>(dtoList, pageable, userPage.getTotalElements());
		return userRolePage;
	}
	
	
	public Page<UserInfornation> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return staffDao.findAll(pageable);
	}

	@Override
	public Page<UserInfornation> findAll(Specification<UserInfornation> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return staffDao.findAll(spec,pageable);
	}

	@Override
	public void userRoleUpdate(String userId, Integer roleId) {
		staffDao.userRoleUpdate(userId, roleId);
	}


}
