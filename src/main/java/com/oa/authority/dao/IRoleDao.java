package com.oa.authority.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.authority.entity.Role;

public interface IRoleDao extends PagingAndSortingRepository<Role,Integer> {
	
	@Query("from Role r where r.roleLevel < ?1")
	public List<Role> findRoleByLevel(Integer roleLevel);

}
