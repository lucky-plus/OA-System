package com.oa.staff.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.oa.message.entity.Notice;
import com.oa.staff.entity.UserInfornation;

public interface IStaffDao extends PagingAndSortingRepository<UserInfornation, String> ,JpaSpecificationExecutor<UserInfornation>{

	public UserInfornation findByUserName(String userName);
	
	@Query("from UserInfornation u where u.role.roleLevel < ?1")
	public Page<UserInfornation> findUserRole(Integer roleLevel, Pageable pageable);

	@Modifying
	@Transactional
	@Query("update UserInfornation u set u.role.roleId = ?2 where u.userId = ?1")
	public void userRoleUpdate(String userId, Integer roleId);
	
	@Query("select mail from UserInfornation u where u.userId = ?1")
	public String findMailByUserId(String userId);
	
}
