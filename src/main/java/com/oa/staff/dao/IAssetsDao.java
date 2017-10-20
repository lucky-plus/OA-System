package com.oa.staff.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.oa.staff.entity.Assets;


@Component
public interface IAssetsDao extends PagingAndSortingRepository<Assets, Integer>,JpaSpecificationExecutor<Assets> {

	@Query("from Assets a where a.user.userId = ?1")
	public List<Assets> findAssetsByUserId(String userId);
	
}
