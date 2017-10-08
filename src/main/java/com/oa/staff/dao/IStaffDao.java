package com.oa.staff.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.message.entity.Notice;
import com.oa.staff.entity.UserInfornation;

public interface IStaffDao extends PagingAndSortingRepository<UserInfornation, String> {

	public UserInfornation findByUserName(String userName);
	
	@Query("from UserInfornation u where u.role.roleLevel > ?1")
	public Page<UserInfornation> findUserRole(Integer roleLevel, Pageable pageable);
	
}
