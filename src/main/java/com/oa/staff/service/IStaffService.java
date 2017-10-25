package com.oa.staff.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


import com.oa.staff.entity.UserInfornation;
import com.oa.staff.entity.dto.PostUserDTO;
import com.oa.staff.entity.dto.TaskUserDTO;
import com.oa.staff.entity.dto.UserRoleDTO;

public interface IStaffService {

	//CRUD
	public void save(PostUserDTO dto);
	public void delete(UserInfornation entity);
	public void delete(String[] ids);
	public UserInfornation findByUserName(String userName);
	public List<PostUserDTO> findAll();
	public Page<PostUserDTO> findAll(Integer roleLevel, Pageable pageable);
	public Page<PostUserDTO> findAll(Specification<UserInfornation> spec, Pageable pageable);
	
	public Page<UserRoleDTO> findUserRole(Integer roleLevel, Pageable pageable); 
	public Page<UserRoleDTO> findUserRoleByCondition(Specification<UserInfornation> spec, Pageable pageable); 
	
	//修改用户权限
	public void userRoleUpdate(String userId, Integer roleId);
	
	//分配任务查找接收者
	public List<TaskUserDTO> findTaskUser(Integer roleLevel);
	
	//根据用户Id查找用户信息
	public PostUserDTO findUserByUserId(String userId);

	public List<TaskUserDTO> findAllTaskUser();
	
	//修改用户头像
	public void updatePictureFileName(String userId, String pictureFileName);
	
	public Page<PostUserDTO> findAddress(Integer roleLevel, Pageable pageable);
	
}
