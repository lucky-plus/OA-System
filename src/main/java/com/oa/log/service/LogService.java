package com.oa.log.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oa.log.dao.ILogDao;
import com.oa.log.entity.Log;
import com.oa.log.entity.dto.LogDTO;

@Service
public class LogService implements ILogService {

	@Autowired
	private ILogDao logDao;
	
	@Override
	public void save(Log log) {
		logDao.save(log);
	}

	@Override
	public Page<LogDTO> findAll(Pageable pageable) {
		Page<Log> logPage = logDao.findAll(pageable);
		List<LogDTO> dtoList = new ArrayList<LogDTO>();
		for(Log entity : logPage.getContent()) {
			LogDTO dto = new LogDTO();
			LogDTO.entityToDto(entity, dto);
			dtoList.add(dto);
		}
		PageImpl<LogDTO> page = new PageImpl<LogDTO>(dtoList, pageable, logPage.getTotalElements());
		return page;
	}

}
