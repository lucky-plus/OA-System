package com.oa.business.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.business.entity.Task;

public interface ITaskDao extends PagingAndSortingRepository<Task, Integer>, JpaSpecificationExecutor<Task> {

}
