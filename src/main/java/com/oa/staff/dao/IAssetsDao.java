package com.oa.staff.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.oa.staff.entity.Assets;


@Component
public interface IAssetsDao extends PagingAndSortingRepository<Assets, Integer>,JpaSpecificationExecutor<Assets> {

}
