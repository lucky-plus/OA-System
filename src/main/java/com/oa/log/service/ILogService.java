package com.oa.log.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.oa.log.entity.Log;
import com.oa.log.entity.dto.LogDTO;

public interface ILogService {
	
	public void save(Log log);
	public Page<LogDTO> findAll(Pageable pageable);
	public Page<LogDTO> findAll(Specification<Log> spec, Pageable pageable);
	
}
