package com.oa.authority.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.authority.entity.Module;

public interface IModuleDao extends PagingAndSortingRepository<Module,Integer> {

}
