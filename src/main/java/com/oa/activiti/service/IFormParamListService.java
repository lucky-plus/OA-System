package com.oa.activiti.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.oa.activiti.entity.formParamList;
import com.oa.activiti.entity.dto.formQueryDTO;

public interface IFormParamListService {
	public void save(formParamList fpl);
	public void delete(formParamList fpl);
	public void delete(Integer id);
	public Page<formQueryDTO> findAll();
	public Page<formQueryDTO> findAll(Pageable pageable);
	public Page<formQueryDTO> findAll(Specification<formParamList> spec, Pageable pageable);

}
