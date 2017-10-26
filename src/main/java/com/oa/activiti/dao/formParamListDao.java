package com.oa.activiti.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.activiti.entity.formParamList;

public interface formParamListDao extends PagingAndSortingRepository<formParamList, Integer>, JpaSpecificationExecutor<formParamList>{

}
