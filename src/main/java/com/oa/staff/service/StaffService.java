package com.oa.staff.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import com.oa.staff.dao.IStaffDao;
import com.oa.staff.entity.UserInfornation;
import com.oa.staff.entity.dto.PostUserDTO;
import com.oa.staff.entity.dto.TaskUserDTO;
import com.oa.staff.entity.dto.UserRoleDTO;

import sun.launcher.resources.launcher;

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
	

	//通讯录、员工管理
	public Page<PostUserDTO> findAll(Pageable pageable) {

		Page<UserInfornation>list=staffDao.findAll(pageable);
		List<PostUserDTO> dtoList=new ArrayList<PostUserDTO>();
		for(UserInfornation user : list.getContent()) {
			PostUserDTO dto = new PostUserDTO();
			PostUserDTO.entityToDto(user, dto);
			dtoList.add(dto);
		}
		PageImpl<PostUserDTO> page = new PageImpl<PostUserDTO>(dtoList, pageable, list.getTotalElements());
		return page;
	}

	@Override
	public Page<PostUserDTO> findAll(Specification<UserInfornation> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<UserInfornation> userPage=staffDao.findAll(spec,pageable);
		List<PostUserDTO> dtoList = new ArrayList<PostUserDTO>();
		if(userPage != null) {
			for(UserInfornation user : userPage.getContent()) {
				PostUserDTO dto = new PostUserDTO();
				PostUserDTO.entityToDto(user, dto);
				dtoList.add(dto);
			}
		}
		PageImpl<PostUserDTO> page = new PageImpl<PostUserDTO>(dtoList, pageable, dtoList.size());
		return page;
	}

	@Override
	public void userRoleUpdate(String userId, Integer roleId) {
		staffDao.userRoleUpdate(userId, roleId);
	}


	@Override
	public void save(PostUserDTO dto) {
		UserInfornation entity=new UserInfornation();
		PostUserDTO.dtoToEntity(dto, entity);
		if(entity.getUserId() == null || "".equals(entity.getUserId())) {
			String userId = UUID.randomUUID().toString().replaceAll("-", "");
			System.out.println(userId);
			entity.setUserId(userId);
		}
		staffDao.save(entity);
		
	}

	@Override
	public void delete(UserInfornation entity) {
		staffDao.delete(entity);
	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			staffDao.delete(id);
		}
	}

	@Override
	public List<TaskUserDTO> findTaskUser(Integer roleLevel) {
		List<UserInfornation> entityList = staffDao.findTaskUser(roleLevel);
		List<TaskUserDTO> dtoList = new ArrayList<TaskUserDTO>();
		if(entityList.size() > 0) {
			for(UserInfornation user : entityList) {
				TaskUserDTO dto = new TaskUserDTO();
				TaskUserDTO.entityToDto(user, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Override
	public PostUserDTO findUserByUserId(String userId) {
		UserInfornation entity = staffDao.findUserByUserId(userId);
		PostUserDTO dto = new PostUserDTO();
		PostUserDTO.entityToDto(entity, dto);
		return dto;
	}

	
	@Override
	public List<TaskUserDTO> findAllTaskUser() {
		List<UserInfornation> entityList = (List<UserInfornation>) staffDao.findAll();
		List<TaskUserDTO> dtoList = new ArrayList<TaskUserDTO>();
		for(UserInfornation entity : entityList) {
			TaskUserDTO dto = new TaskUserDTO();
			TaskUserDTO.entityToDto(entity, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}


}
