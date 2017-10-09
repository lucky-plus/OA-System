package com.oa.authority.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.authority.entity.dto.ModuleDTO;
import com.oa.authority.service.IModuleService;

@Controller
@RequestMapping("/module")
public class ModuleController {
	
	@Autowired
	private IModuleService moduleService;

	@RequestMapping("/findAll")
	public @ResponseBody List<ModuleDTO> findAll() {
		return moduleService.findAll();
	}
	
}
