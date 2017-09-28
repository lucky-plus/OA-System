package com.oa.authority.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.authority.entity.Role;

public interface IRoleDao extends PagingAndSortingRepository<Role,Integer> {

}
