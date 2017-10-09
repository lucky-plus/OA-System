package com.oa.message.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.oa.message.entity.Resources;

@Component
public interface IResourcesDao extends PagingAndSortingRepository<Resources, Integer>, JpaSpecificationExecutor<Resources> {

}
