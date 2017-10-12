package com.oa.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import com.oa.staff.dao.IStaffDao;
import com.oa.staff.entity.UserInfornation;
import com.oa.staff.entity.dto.UserPostDTO;
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
	
	
	//////通讯录、员工管理
	public Page<UserPostDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<UserInfornation>list=staffDao.findAll(pageable);
		List<UserPostDTO> dtoList=new ArrayList<UserPostDTO>();
		for(UserInfornation user : list.getContent()) {
			UserPostDTO dto = new UserPostDTO();
			UserPostDTO.entityToDto(user, dto);
			dtoList.add(dto);
		}
		PageImpl<UserPostDTO> page = new PageImpl<UserPostDTO>(dtoList, pageable, list.getTotalElements());
		return page;
	}

	@Override
	public Page<UserPostDTO> findAll(Specification<UserInfornation> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<UserInfornation> userPage=staffDao.findAll(spec,pageable);
		List<UserPostDTO> dtoList = new ArrayList<UserPostDTO>();
		if(userPage != null) {
			for(UserInfornation user : userPage.getContent()) {
				UserPostDTO dto = new UserPostDTO();
				UserPostDTO.entityToDto(user, dto);
				dtoList.add(dto);
			}
		}
		PageImpl<UserPostDTO> page = new PageImpl<UserPostDTO>(dtoList, pageable, dtoList.size());
		return page;
	}

	@Override
	public void userRoleUpdate(String userId, Integer roleId) {
		staffDao.userRoleUpdate(userId, roleId);
	}

	@Override
	public void save(UserPostDTO dto) {
		UserInfornation entity=new UserInfornation();
		UserPostDTO.dtoToEntity(dto, entity);
		staffDao.save(entity);
		
	}

	@Override
	public void delete(UserInfornation entity) {
		staffDao.delete(entity);
	}

	public void delete(String id) {
		staffDao.delete(id);
	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			staffDao.delete(id);
		}
	}

}
