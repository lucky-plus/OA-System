package com.oa.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.oa.business.entity.Contract;
import com.oa.business.entity.dto.ContractDTO;


public interface IContractService {
	public void save(ContractDTO dto);
	public void delete(Contract entity);
	public void delete(Integer id);
	public void delete(Integer[] ids);
	public Contract findOne(Integer id);
	public List<ContractDTO> findAll();
	public Page<ContractDTO> findAll(Pageable pageable);
	public Page<ContractDTO> findAll(Specification<Contract> spec, Pageable pageable);
}
