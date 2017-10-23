package com.oa.business.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.business.entity.Contract;

public interface IContractDao extends PagingAndSortingRepository<Contract, Integer>, JpaSpecificationExecutor<Contract> {

}
