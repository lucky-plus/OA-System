package com.oa.staff.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.staff.entity.UserInfornation;

public interface IStaffDao extends PagingAndSortingRepository<UserInfornation, String> {

	public UserInfornation findByUserName(String userName);

}
