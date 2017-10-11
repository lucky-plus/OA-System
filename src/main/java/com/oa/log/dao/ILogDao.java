package com.oa.log.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.log.entity.Log;

public interface ILogDao extends PagingAndSortingRepository<Log, Integer> {

}
