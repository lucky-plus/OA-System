package com.oa.staff.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.staff.entity.UserInfornation;

public interface IStaffDao extends PagingAndSortingRepository<UserInfornation, String> ,JpaSpecificationExecutor<UserInfornation>{

	public UserInfornation findByUserName(String userName);

}
