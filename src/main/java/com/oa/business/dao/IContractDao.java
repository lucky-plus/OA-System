package com.oa.business.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.oa.business.entity.Contract;

public interface IContractDao extends PagingAndSortingRepository<Contract, Integer>, JpaSpecificationExecutor<Contract> {

	
	@Modifying
	@Transactional
	@Query("update Contract c set c.pictureFileName = ?2 where c.contractId = ?1")
	public void updatePictureFileName(Integer contractId, String pictureFileName);
	
}
