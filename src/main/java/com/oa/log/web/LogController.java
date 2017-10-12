package com.oa.log.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.log.entity.dto.LogDTO;
import com.oa.log.service.ILogService;
import com.oa.utils.ExtjsPageable;

@Controller
@RequestMapping("/log")
public class LogController {

	@Autowired
	private ILogService logService;
	
	@RequestMapping("/findAll")
	public @ResponseBody Page<LogDTO> findAll(ExtjsPageable pageable) {
		return logService.findAll(pageable.getPageable());
	}
	
	@RequestMapping("/findByCondition")
	public @ResponseBody Page<LogDTO> findByCondition(LogDTO logDTO, ExtjsPageable pageable) {
		return logService.findAll(LogDTO.getWhereClause(logDTO), pageable.getPageable());
	}
	
}
